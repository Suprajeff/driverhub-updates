package com.deliveryhub.uberwatcher.material.adaptive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Calculates and returns [WindowAdaptiveInfo] of the provided context. It's a convenient function
 * that uses the default [WindowSizeClass] constructor and the default [Posture] calculation
 * functions to retrieve [WindowSizeClass] and [Posture].
 *
 * @return [WindowAdaptiveInfo] of the provided context
 */
@Composable
fun currentWindowAdaptiveInfo(): WindowAdaptiveInfo {
    val context = LocalContext.current
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    val windowSize = DpSize(screenWidthDp, screenHeightDp)

    val windowSizeClass = when {
        screenWidthDp < 600.dp -> WindowSizeClass(
            minWidthDp = WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND,
            minHeightDp = WindowSizeClass.Companion.HEIGHT_DP_MEDIUM_LOWER_BOUND
        )
        screenWidthDp < 840.dp -> WindowSizeClass(
            minWidthDp = WindowSizeClass.Companion.WIDTH_DP_LARGE_LOWER_BOUND,
            minHeightDp = WindowSizeClass.Companion.HEIGHT_DP_MEDIUM_LOWER_BOUND
        )
        else -> WindowSizeClass(
            minWidthDp = WindowSizeClass.Companion.WIDTH_DP_EXPANDED_LOWER_BOUND,
            minHeightDp = WindowSizeClass.Companion.HEIGHT_DP_EXPANDED_LOWER_BOUND
        )
    }

    val windowPosture = Posture(isTabletop = false, hingeList = emptyList())

    return WindowAdaptiveInfo(
        windowSizeClass = windowSizeClass,
        windowPosture = windowPosture
    )
}

/**
 * This class collects window info that affects adaptation decisions. An adaptive layout is supposed
 * to use the info from this class to decide how the layout is supposed to be adapted.
 *
 * @param windowSizeClass [WindowSizeClass] of the current window.
 * @param windowPosture [Posture] of the current window.
 * @constructor create an instance of [WindowAdaptiveInfo]
 */
@Immutable
class WindowAdaptiveInfo(val windowSizeClass: WindowSizeClass, val windowPosture: Posture) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WindowAdaptiveInfo) return false
        if (windowSizeClass != other.windowSizeClass) return false
        if (windowPosture != other.windowPosture) return false
        return true
    }

    override fun hashCode(): Int {
        var result = windowSizeClass.hashCode()
        result = 31 * result + windowPosture.hashCode()
        return result
    }

    override fun toString(): String {
        return "WindowAdaptiveInfo(windowSizeClass=$windowSizeClass, windowPosture=$windowPosture)"
    }
}