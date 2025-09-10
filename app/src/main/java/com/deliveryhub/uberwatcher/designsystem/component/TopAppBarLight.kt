package com.deliveryhub.uberwatcher.designsystem.component

import android.R
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deliveryhub.uberwatcher.designsystem.icon.UberWatcherIcons
import com.deliveryhub.uberwatcher.designsystem.theme.UberWatcherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UberWatcherTopAppBarLight(
    @StringRes titleRes: Int,
    profileImage: Painter?,
    navigationIconContentDescription: String,
    actionIcon: Painter,
    actionIconContentDescription: String,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        navigationIcon = {
            if (profileImage != null) {
                Image(
                    painter = profileImage,
                    contentDescription = "Profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .clickable(onClick = onNavigationClick)
                )
            } else {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = UberWatcherIcons.Person,
                        contentDescription = "Profile picture placeholder",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
//            IconButton(onClick = onNavigationClick) {
//                Icon(
//                    imageVector = profilePicture,
//                    contentDescription = navigationIconContentDescription,
//                    tint = MaterialTheme.colorScheme.onSurface,
//                )
//            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    painter = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(24.dp)
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
private fun UberWatcherTopAppBarLightPreview() {
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