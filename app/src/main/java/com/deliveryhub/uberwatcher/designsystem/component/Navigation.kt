package com.deliveryhub.uberwatcher.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import com.deliveryhub.uberwatcher.material.Icon
import com.deliveryhub.uberwatcher.material.MaterialTheme
import com.deliveryhub.uberwatcher.material.NavigationBar
import com.deliveryhub.uberwatcher.material.NavigationBarItem
import com.deliveryhub.uberwatcher.material.NavigationBarItemDefaults
import com.deliveryhub.uberwatcher.material.NavigationDrawerItemDefaults
import com.deliveryhub.uberwatcher.material.NavigationRail
import com.deliveryhub.uberwatcher.material.NavigationRailItem
import com.deliveryhub.uberwatcher.material.NavigationRailItemDefaults
import com.deliveryhub.uberwatcher.material.Text
import com.deliveryhub.uberwatcher.material.adaptive.ExperimentalMaterial3AdaptiveApi
import com.deliveryhub.uberwatcher.material.adaptive.WindowAdaptiveInfo
import com.deliveryhub.uberwatcher.material.adaptive.currentWindowAdaptiveInfo
import com.deliveryhub.uberwatcher.material.adaptive.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import com.deliveryhub.uberwatcher.material.adaptive.NavigationSuiteDefaults
import com.deliveryhub.uberwatcher.material.adaptive.NavigationSuiteItemColors
import com.deliveryhub.uberwatcher.material.adaptive.NavigationSuiteScaffold
import com.deliveryhub.uberwatcher.material.adaptive.NavigationSuiteScaffoldDefaults
import com.deliveryhub.uberwatcher.material.adaptive.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deliveryhub.uberwatcher.designsystem.icon.UberWatcherIcons
import com.deliveryhub.uberwatcher.designsystem.theme.UberWatcherTheme

/**
 * UberWatcher navigation bar item with icon and label content slots. Wraps Material 3
 * [NavigationBarItem].
 *
 * @param selected Whether this item is selected.
 * @param onClick The callback to be invoked when this item is selected.
 * @param icon The item icon content.
 * @param modifier Modifier to be applied to this item.
 * @param selectedIcon The item icon content when selected.
 * @param enabled controls the enabled state of this item. When `false`, this item will not be
 * clickable and will appear disabled to accessibility services.
 * @param label The item text label content.
 * @param alwaysShowLabel Whether to always show the label for this item. If false, the label will
 * only be shown when this item is selected.
 */
@Composable
fun RowScope.UberWatcherNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = UberWatcherNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = UberWatcherNavigationDefaults.navigationContentColor(),
            selectedTextColor = UberWatcherNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = UberWatcherNavigationDefaults.navigationContentColor(),
            indicatorColor = UberWatcherNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

/**
 * UberWatcher navigation bar with content slot. Wraps Material 3 [NavigationBar].
 *
 * @param modifier Modifier to be applied to the navigation bar.
 * @param content Destinations inside the navigation bar. This should contain multiple
 * [NavigationBarItem]s.
 */
@Composable
fun UberWatcherNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = UberWatcherNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}

/**
 * UberWatcher navigation rail item with icon and label content slots. Wraps Material 3
 * [NavigationRailItem].
 *
 * @param selected Whether this item is selected.
 * @param onClick The callback to be invoked when this item is selected.
 * @param icon The item icon content.
 * @param modifier Modifier to be applied to this item.
 * @param selectedIcon The item icon content when selected.
 * @param enabled controls the enabled state of this item. When `false`, this item will not be
 * clickable and will appear disabled to accessibility services.
 * @param label The item text label content.
 * @param alwaysShowLabel Whether to always show the label for this item. If false, the label will
 * only be shown when this item is selected.
 */
@Composable
fun UberWatcherNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = UberWatcherNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = UberWatcherNavigationDefaults.navigationContentColor(),
            selectedTextColor = UberWatcherNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = UberWatcherNavigationDefaults.navigationContentColor(),
            indicatorColor = UberWatcherNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

/**
 * UberWatcher navigation rail with header and content slots. Wraps Material 3 [NavigationRail].
 *
 * @param modifier Modifier to be applied to the navigation rail.
 * @param header Optional header that may hold a floating action button or a logo.
 * @param content Destinations inside the navigation rail. This should contain multiple
 * [NavigationRailItem]s.
 */
@Composable
fun UberWatcherNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = UberWatcherNavigationDefaults.navigationContentColor(),
        header = header,
        content = content,
    )
}

/**
 * UberWatcher navigation suite scaffold with item and content slots.
 * Wraps Material 3 [NavigationSuiteScaffold].
 *
 * @param modifier Modifier to be applied to the navigation suite scaffold.
 * @param navigationSuiteItems A slot to display multiple items via [UberWatcherNavigationSuiteScope].
 * @param windowAdaptiveInfo The window adaptive info.
 * @param content The app content inside the scaffold.
 */
@OptIn(
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
    ExperimentalMaterial3AdaptiveApi::class,
)
@Composable
fun UberWatcherNavigationSuiteScaffold(
    navigationSuiteItems: UberWatcherNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit,
) {
    val layoutType = NavigationSuiteScaffoldDefaults
        .calculateFromAdaptiveInfo(windowAdaptiveInfo)
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = UberWatcherNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = UberWatcherNavigationDefaults.navigationContentColor(),
            selectedTextColor = UberWatcherNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = UberWatcherNavigationDefaults.navigationContentColor(),
            indicatorColor = UberWatcherNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = UberWatcherNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = UberWatcherNavigationDefaults.navigationContentColor(),
            selectedTextColor = UberWatcherNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = UberWatcherNavigationDefaults.navigationContentColor(),
            indicatorColor = UberWatcherNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = UberWatcherNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = UberWatcherNavigationDefaults.navigationContentColor(),
            selectedTextColor = UberWatcherNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = UberWatcherNavigationDefaults.navigationContentColor(),
        ),
    )

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            UberWatcherNavigationSuiteScope(
                navigationSuiteScope = this,
                navigationSuiteItemColors = navigationSuiteItemColors,
            ).run(navigationSuiteItems)
        },
        layoutType = layoutType,
        containerColor = Color.Transparent,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContentColor = UberWatcherNavigationDefaults.navigationContentColor(),
            navigationRailContainerColor = Color.Transparent,
        ),
        modifier = modifier,
    ) {
        content()
    }
}

/**
 * A wrapper around [NavigationSuiteScope] to declare navigation items.
 */
@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
class UberWatcherNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
fun UberWatcherNavigationBarPreview() {
    val items = listOf("For you", "Saved", "Interests")
    val icons = listOf(
        UberWatcherIcons.UpcomingBorder,
        UberWatcherIcons.BookmarksBorder,
        UberWatcherIcons.Grid3x3,
    )
    val selectedIcons = listOf(
        UberWatcherIcons.Upcoming,
        UberWatcherIcons.Bookmarks,
        UberWatcherIcons.Grid3x3,
    )

    UberWatcherTheme {
        UberWatcherNavigationBar {
            items.forEachIndexed { index, item ->
                UberWatcherNavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun UberWatcherNavigationRailPreview() {
    val items = listOf("Userbase", "Onboarding", "Logs")
    val icons = listOf(
        UberWatcherIcons.UpcomingBorder,
        UberWatcherIcons.BookmarksBorder,
        UberWatcherIcons.Grid3x3,
    )
    val selectedIcons = listOf(
        UberWatcherIcons.Upcoming,
        UberWatcherIcons.Bookmarks,
        UberWatcherIcons.Grid3x3,
    )

    UberWatcherTheme {
        UberWatcherNavigationRail {
            items.forEachIndexed { index, item ->
                UberWatcherNavigationRailItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

/**
 * UberWatcher navigation default values.
 */
object UberWatcherNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}