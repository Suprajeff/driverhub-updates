package com.deliveryhub.uberwatcher.material.internal

import androidx.compose.ui.text.PlatformTextStyle

/** Returns Default [PlatformTextStyle]. */
internal fun defaultPlatformTextStyle(): PlatformTextStyle? {
    // For Android, the default platform text style is typically null,
    // letting Compose handle it normally, or a specific instance if needed for
    // things like includeFontPadding or specific line height tweaks.
    // If the original KMP actual for Android returned a specific PlatformTextStyle,
    // you would put that specific instance here.
    return null
}