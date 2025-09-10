package com.deliveryhub.uberwatcher.material.internal

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import androidx.activity.compose.BackHandler as AndroidBackHandler

// TODO(b/352352908): Remove once this API will be available in common
internal class BackEventCompat(
    val touchX: Float = 0f,
    val touchY: Float = 0f,
    val progress: Float = 0f,
    val swipeEdge: Int = EDGE_LEFT,
) {
    companion object {
        const val EDGE_LEFT: Int = 0
        const val EDGE_RIGHT: Int = 1
    }
}

// TODO(b/352352908): Remove once this API will be available in common
@Composable
internal fun BackHandler(enabled: Boolean = true, onBack: () -> Unit) {
    AndroidBackHandler(enabled = enabled, onBack = onBack)
}

// TODO(b/352352908): Remove once this API will be available in common
@Composable
internal fun PredictiveBackHandler(
    enabled: Boolean = true,
    onBack: suspend (progress: Flow<BackEventCompat>) -> Unit,
) {
    // Stub: do nothing
}

private val PredictiveBackEasing: Easing = CubicBezierEasing(0.1f, 0.1f, 0f, 1f)

internal object PredictiveBack {
    internal fun transform(progress: Float) = PredictiveBackEasing.transform(progress)
}