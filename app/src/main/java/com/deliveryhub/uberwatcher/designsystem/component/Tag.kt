package com.deliveryhub.uberwatcher.designsystem.component

import androidx.compose.foundation.layout.Box
import com.deliveryhub.uberwatcher.material.ButtonDefaults
import com.deliveryhub.uberwatcher.material.MaterialTheme
import com.deliveryhub.uberwatcher.material.ProvideTextStyle
import com.deliveryhub.uberwatcher.material.Text
import com.deliveryhub.uberwatcher.material.TextButton
import com.deliveryhub.uberwatcher.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.deliveryhub.uberwatcher.designsystem.theme.UberWatcherTheme

@Composable
fun UberWatcherTopicTag(
    modifier: Modifier = Modifier,
    followed: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        val containerColor = if (followed) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant.copy(
                alpha = UberWatcherTagDefaults.UNFOLLOWED_TOPIC_TAG_CONTAINER_ALPHA,
            )
        }
        TextButton(
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.textButtonColors(
                containerColor = containerColor,
                contentColor = contentColorFor(backgroundColor = containerColor),
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = UberWatcherTagDefaults.DISABLED_TOPIC_TAG_CONTAINER_ALPHA,
                ),
            ),
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                text()
            }
        }
    }
}

@ThemePreviews
@Composable
fun TagPreview() {
    UberWatcherTheme {
        UberWatcherTopicTag(followed = true, onClick = {}) {
            Text("Topic".uppercase())
        }
    }
}

/**
 * UberWatcher tag default values.
 */
object UberWatcherTagDefaults {
    const val UNFOLLOWED_TOPIC_TAG_CONTAINER_ALPHA = 0.5f

    // TODO: File bug
    // Button disabled container alpha value not exposed by ButtonDefaults
    const val DISABLED_TOPIC_TAG_CONTAINER_ALPHA = 0.12f
}