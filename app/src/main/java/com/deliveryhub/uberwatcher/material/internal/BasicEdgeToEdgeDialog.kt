package com.deliveryhub.uberwatcher.material.internal

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlinx.coroutines.CancellationException

@Composable
internal fun BasicEdgeToEdgeDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    lightStatusBars: Boolean = LocalContentColor.current.luminance() < 0.5f,
    lightNavigationBars: Boolean = LocalContentColor.current.luminance() < 0.5f,
    content: @Composable (PredictiveBackState) -> Unit,
) {
    val view = LocalView.current
    val window = (view.context as? Activity)?.window

    // Enable edge-to-edge content
    window?.let {
        WindowCompat.setDecorFitsSystemWindows(it, false)
        val controller = WindowInsetsControllerCompat(it, view)
        controller.isAppearanceLightStatusBars = lightStatusBars
        controller.isAppearanceLightNavigationBars = lightNavigationBars
    }

    val state = rememberPredictiveBackState()

    // Handle predictive back via your handler
    PredictiveBackStateHandler(
        state = state,
        onBack = onDismissRequest,
    )

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Box(modifier = modifier) {
            content(state)
        }
    }
}

internal sealed interface PredictiveBackState {
    val value: BackEventProgress
}

/**
 * The state describing a one-shot back state, with use in a [PredictiveBackStateHandler].
 *
 * Because the back handler can only be used once, there are three states that [BackEventProgress]
 * can be in:
 * - [NotRunning]
 * - [InProgress], which can happen on API 34 and above if a predictive back is in progress.
 * - [Completed]
 */
internal sealed interface BackEventProgress {
    /** There is no predictive back ongoing, and the back has not been completed. */
    object NotRunning : BackEventProgress

    /** There is an ongoing predictive back animation, with the given [progress]. */
    data class InProgress(
        val touchX: Float,
        val touchY: Float,
        val progress: Float,
        val swipeEdge: SwipeEdge,
    ) : BackEventProgress

    /** The back has completed. */
    object Completed : BackEventProgress
}

internal enum class SwipeEdge {
    Left,
    Right,
    None,
}

@Composable
internal fun rememberPredictiveBackState(): PredictiveBackState = remember {
    PredictiveBackStateImpl()
}

private class PredictiveBackStateImpl : PredictiveBackState {
    override var value: BackEventProgress by mutableStateOf(BackEventProgress.NotRunning)
}

@Composable
internal fun PredictiveBackStateHandler(
    state: PredictiveBackState,
    enabled: Boolean = true,
    onBack: () -> Unit,
) {
    // Safely update the current `onBack` lambda when a new one is provided
    val currentOnBack by rememberUpdatedState(onBack)

    key(state) {
        state as PredictiveBackStateImpl
        PredictiveBackHandler(enabled = enabled && state.value !is BackEventProgress.Completed) { progress ->
            try {
                progress.collect { backEvent ->
                    state.value =
                        BackEventProgress.InProgress(
                            backEvent.touchX,
                            backEvent.touchY,
                            backEvent.progress,
                            when (backEvent.swipeEdge) {
                                BackEventCompat.EDGE_LEFT -> SwipeEdge.Left
                                BackEventCompat.EDGE_RIGHT -> SwipeEdge.Right
                                else -> SwipeEdge.None
                            },
                        )
                }
                state.value = BackEventProgress.Completed
                currentOnBack()
            } catch (e: CancellationException) {
                state.value = BackEventProgress.NotRunning
                throw e
            }
        }
    }
}