package com.deliveryhub.uberwatcher

import android.Manifest.permission
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.deliveryhub.uberwatcher.ui.theme.DriverTheme
import io.ktor.client.HttpClient
import android.os.PowerManager
import androidx.activity.SystemBarStyle
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.deliveryhub.uberwatcher.composables.DeliverooOrderListing
import com.deliveryhub.uberwatcher.composables.UberOrderListing
import com.deliveryhub.uberwatcher.data.CustomerRepository
import com.deliveryhub.uberwatcher.data.OrderRepository
import com.deliveryhub.uberwatcher.data.util.NetworkMonitor
import com.deliveryhub.uberwatcher.db.di.DAOHolder
import com.deliveryhub.uberwatcher.db.models.asExternalModel
import com.deliveryhub.uberwatcher.designsystem.theme.UberWatcherTheme
import com.deliveryhub.uberwatcher.models.types.Order
import com.deliveryhub.uberwatcher.states.UberWatcherApp
import com.deliveryhub.uberwatcher.states.rememberUberWatcherAppState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : ComponentActivity() {
    lateinit var networkMonitor: NetworkMonitor
    lateinit var customerRepository: CustomerRepository
    lateinit var orderRepository: OrderRepository


    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        when {
            ContextCompat.checkSelfPermission(
                this,
                permission.POST_NOTIFICATIONS
            ) == PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, permission.POST_NOTIFICATIONS) -> {

            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                // You can directly ask for the permission.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(permission.POST_NOTIFICATIONS),
                        101
                    )
                }
            }
        }

        // Update the uiState
//        lifecycleScope.launch {
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.uiState
//                    .onEach { uiState = it }
//                    .collect()
//            }
//        }

        // Keep the splash screen on-screen until the UI state is loaded. This condition is
        // evaluated each time the app needs to be redrawn so it should be fast to avoid blocking
        // the UI.
//        splashScreen.setKeepOnScreenCondition {
//            when (uiState) {
//                Loading -> true
//                is Success -> false
//            }
//        }

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations, and go edge-to-edge
        // This also sets up the initial system bar style based on the platform theme
        enableEdgeToEdge()

        setContent {

            val darkTheme = true

            // Update the edge to edge configuration to match the theme
            // This is the same parameters as the default enableEdgeToEdge call, but we manually
            // resolve whether or not to show dark theme using uiState, since it can be different
            // than the configuration's dark theme value based on the user preference.
            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.TRANSPARENT,
                        android.graphics.Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim,
                        darkScrim,
                    ) { darkTheme },
                )
                onDispose {}
            }

            val appState = rememberUberWatcherAppState(
                networkMonitor = networkMonitor,
                customerRepository = customerRepository,
                orderRepository = orderRepository,
            )


            
            CompositionLocalProvider(
                
            ) {
                UberWatcherTheme(
                    darkTheme = true,
                ) {
                    UberWatcherApp(appState)
                }
            }
        }

        networkMonitor = (application as WatcherApplication).dataModule.networkMonitor
        customerRepository = (application as WatcherApplication).dataModule.customerRepository
        orderRepository = (application as WatcherApplication).dataModule.orderRepository
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UberWatcherTheme {
        Greeting("Android")
    }
}

/**
 * Returns `true` if dark theme should be used, as a function of the [uiState] and the
 * current system context.
 */
//@Composable
//private fun shouldUseDarkTheme(
//    uiState: MainActivityUiState,
//): Boolean = when (uiState) {
//    Loading -> isSystemInDarkTheme()
//    is Success -> when (uiState.userData.darkThemeConfig) {
//        DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
//        DarkThemeConfig.LIGHT -> false
//        DarkThemeConfig.DARK -> true
//    }
//}

/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

fun Context.requestIgnoreBatteryOptimizations() {
    val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
    val packageName = packageName
    if (!pm.isIgnoringBatteryOptimizations(packageName)) {
        val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    DriverTheme {
//        MainScreen(onEnableClick = {}, )
//    }
//}