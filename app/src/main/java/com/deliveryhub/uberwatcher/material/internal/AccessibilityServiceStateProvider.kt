package com.deliveryhub.uberwatcher.material.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
internal fun rememberAccessibilityServiceState(
    listenToTouchExplorationState: Boolean = true,
    listenToSwitchAccessState: Boolean = true,
    listenToVoiceAccessState: Boolean = true,
): State<Boolean> {
    // Since we are not using platform-specific APIs, return a default value.
    // You can later implement real logic based on what platform you're targeting.
    val isAccessibilityEnabled = remember {
        mutableStateOf(false) // or true depending on your app's logic
    }

    return isAccessibilityEnabled
}
