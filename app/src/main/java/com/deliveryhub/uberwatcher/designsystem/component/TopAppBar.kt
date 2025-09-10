package com.deliveryhub.uberwatcher.designsystem.component

import android.R
import androidx.annotation.StringRes
import com.deliveryhub.uberwatcher.material.CenterAlignedTopAppBar
import com.deliveryhub.uberwatcher.material.ExperimentalMaterial3Api
import com.deliveryhub.uberwatcher.material.Icon
import com.deliveryhub.uberwatcher.material.IconButton
import com.deliveryhub.uberwatcher.material.MaterialTheme
import com.deliveryhub.uberwatcher.material.Text
import com.deliveryhub.uberwatcher.material.TopAppBarColors
import com.deliveryhub.uberwatcher.material.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.deliveryhub.uberwatcher.designsystem.icon.UberWatcherIcons
import com.deliveryhub.uberwatcher.designsystem.theme.UberWatcherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UberWatcherTopAppBar(
    @StringRes titleRes: Int,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String,
    actionIcon: ImageVector,
    actionIconContentDescription: String,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = colors,
        modifier = modifier.testTag("uberWatcherTopAppBar"),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Top App Bar")
@Composable
private fun UberWatcherTopAppBarPreview() {
    UberWatcherTheme {
        UberWatcherTopAppBar(
            titleRes = R.string.untitled,
            navigationIcon = UberWatcherIcons.Search,
            navigationIconContentDescription = "Navigation icon",
            actionIcon = UberWatcherIcons.MoreVert,
            actionIconContentDescription = "Action icon",
        )
    }
}