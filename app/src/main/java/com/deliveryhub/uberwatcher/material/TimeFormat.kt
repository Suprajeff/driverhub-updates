package com.deliveryhub.uberwatcher.material

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import android.text.format.DateFormat

internal val is24HourFormat: Boolean
    @Composable
    @ReadOnlyComposable
    get() {
        val context = LocalContext.current
        return DateFormat.is24HourFormat(context)
    }