package com.deliveryhub.uberwatcher.states

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import com.deliveryhub.uberwatcher.material.ExperimentalMaterial3Api
import com.deliveryhub.uberwatcher.material.Icon
import com.deliveryhub.uberwatcher.material.LocalAbsoluteTonalElevation
import com.deliveryhub.uberwatcher.material.MaterialTheme
import com.deliveryhub.uberwatcher.material.Scaffold
import com.deliveryhub.uberwatcher.material.SnackbarDuration
import com.deliveryhub.uberwatcher.material.SnackbarHost
import com.deliveryhub.uberwatcher.material.SnackbarHostState
import com.deliveryhub.uberwatcher.material.SnackbarResult
import com.deliveryhub.uberwatcher.material.Surface
import com.deliveryhub.uberwatcher.material.Text
import com.deliveryhub.uberwatcher.material.TopAppBarDefaults
import com.deliveryhub.uberwatcher.material.SnackbarDuration.Short
import com.deliveryhub.uberwatcher.material.adaptive.ExperimentalMaterial3AdaptiveApi
import com.deliveryhub.uberwatcher.material.adaptive.WindowAdaptiveInfo
import com.deliveryhub.uberwatcher.material.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.deliveryhub.uberwatcher.R
import com.deliveryhub.uberwatcher.designsystem.component.UberWatcherNavigationSuiteScaffold
import com.deliveryhub.uberwatcher.designsystem.component.UberWatcherTopAppBarLight
import com.deliveryhub.uberwatcher.navigation.TopLevelDestination
import com.deliveryhub.uberwatcher.designsystem.theme.LocalBackgroundTheme
import com.deliveryhub.uberwatcher.navigation.UberWatcherNavHost
import kotlin.reflect.KClass

@Composable
fun UberWatcherApp(
    appState: UberWatcherAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val shouldShowGradientBackground =
        appState.currentTopLevelDestination == TopLevelDestination.ORDERS
    var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

    UberWatcherBackground(modifier = modifier) {
        //UberWatcherGradientBackground {

        val snackbarHostState = remember { SnackbarHostState() }

        val navController = appState.navController

        val isOffline by appState.isOffline.collectAsStateWithLifecycle()

        // If user is not connected to the internet show a snack bar to inform them.
        val notConnectedMessage = stringResource(R.string.not_connected)

        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackbarHostState.showSnackbar(
                    message = notConnectedMessage,
                    duration = SnackbarDuration.Indefinite,
                )
            }
        }

        UberWatcherApp(
            appState = appState,
            snackbarHostState = snackbarHostState,
            onTopAppBarActionClick = { showSettingsDialog = true },
            windowAdaptiveInfo = windowAdaptiveInfo,
        )

        //}
    }

}

@Composable
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class,
    ExperimentalMaterial3AdaptiveApi::class,
)
internal fun UberWatcherApp(
    appState: UberWatcherAppState,
    snackbarHostState: SnackbarHostState,
    onTopAppBarActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
//    val unreadDestinations by appState.topLevelDestinationsWithUnreadResources
//        .collectAsStateWithLifecycle()
    val currentDestination = appState.currentDestination


        UberWatcherNavigationSuiteScaffold(
            navigationSuiteItems = {
                appState.topLevelDestinations.forEach { destination ->
                    //val hasUnread = unreadDestinations.contains(destination)
                    val selected = currentDestination
                        .isRouteInHierarchy(destination.route)
                    item(
                        selected = selected,
                        onClick = { appState.navigateToTopLevelDestination(destination) },
                        icon = {
                            Icon(
                                imageVector = destination.unselectedIcon,
                                contentDescription = null,
                            )
                        },
                        selectedIcon = {
                            Icon(
                                imageVector = destination.selectedIcon,
                                contentDescription = null,
                            )
                        },
                        label = { Text(stringResource(destination.iconTextId)) },
                        modifier =
                            Modifier
                                .testTag("UberWatcherNavItem")
                        //.then(if (hasUnread) Modifier.notificationDot() else Modifier),
                    )
                }
            },
            windowAdaptiveInfo = windowAdaptiveInfo,
        ) {
            MainScaffoldContent(
                appState = appState,
                snackbarHostState = snackbarHostState,
                onTopAppBarActionClick = onTopAppBarActionClick,
                modifier = modifier,
            )
        }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScaffoldContent(
    appState: UberWatcherAppState,
    snackbarHostState: SnackbarHostState,
    onTopAppBarActionClick: () -> Unit,
    modifier: Modifier = Modifier,
){

    Scaffold(
        modifier = modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            // Show the top app bar on top level destinations.
            val destination = appState.currentTopLevelDestination
            val shouldShowTopAppBar = false
//
//            if (destination != null) {
//                shouldShowTopAppBar = true
//                UberWatcherTopAppBarLight(
//                    titleRes = destination.titleTextId,
//                    profileImage = painterResource(id = R.drawable.lucia),
//                    navigationIconContentDescription = stringResource(
//                        id = settingsR.string.feature_settings_top_app_bar_navigation_icon_description,
//                    ),
//                    actionIcon = painterResource(id = R.drawable.notifications),
//                    actionIconContentDescription = stringResource(
//                        id = settingsR.string.feature_settings_top_app_bar_action_icon_description,
//                    ),
//                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                        containerColor = Color.Transparent,
//                    ),
//                    onActionClick = { onTopAppBarActionClick() },
//                    onNavigationClick = {  },
//                )
//            }

            Box(
                // Workaround for https://issuetracker.google.com/338478720
                modifier = Modifier.consumeWindowInsets(
                    if (shouldShowTopAppBar) {
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                    } else {
                        WindowInsets(0, 0, 0, 0)
                    },
                ),
            ) {
                UberWatcherNavHost(
                    appState = appState,
                    onShowSnackbar = { message, action ->
                        snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = action,
                            duration = Short,
                        ) == SnackbarResult.ActionPerformed
                    },
                )
            }

            // TODO: We may want to add padding or spacer when the snackbar is shown so that
            //  content doesn't display behind it.
        }
    }

}

private fun Modifier.notificationDot(): Modifier =
    composed {
        val tertiaryColor = MaterialTheme.colorScheme.tertiary
        drawWithContent {
            drawContent()
            drawCircle(
                tertiaryColor,
                radius = 5.dp.toPx(),
                // This is based on the dimensions of the NavigationBar's "indicator pill";
                // however, its parameters are private, so we must depend on them implicitly
                // (NavigationBarTokens.ActiveIndicatorWidth = 64.dp)
                center = center + Offset(
                    64.dp.toPx() * .45f,
                    32.dp.toPx() * -.45f - 6.dp.toPx(),
                ),
            )
        }
    }

@Composable
fun UberWatcherBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {

    val color = LocalBackgroundTheme.current.color
    val tonalElevation = LocalBackgroundTheme.current.tonalElevation
    Surface(
        color = if (color == Color.Unspecified) Color.Transparent else color,
        tonalElevation = if (tonalElevation == Dp.Unspecified) 0.dp else tonalElevation,
        modifier = modifier.fillMaxSize(),
    ) {
        CompositionLocalProvider(LocalAbsoluteTonalElevation provides 0.dp) {
            content()
        }
    }

}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false