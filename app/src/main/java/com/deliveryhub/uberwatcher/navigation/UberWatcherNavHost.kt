package com.deliveryhub.uberwatcher.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.deliveryhub.uberwatcher.screens.customers.navigation.customerScreen
import com.deliveryhub.uberwatcher.screens.orders.OrdersRoute
import com.deliveryhub.uberwatcher.screens.orders.navigation.OrderRoute
import com.deliveryhub.uberwatcher.screens.orders.navigation.orderScreen
import com.deliveryhub.uberwatcher.states.UberWatcherAppState

@Composable
fun UberWatcherNavHost(
    appState: UberWatcherAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
) {

    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = OrderRoute,
        modifier = modifier,
    ) {
        orderScreen(
            onOrderClick = {
//                navController.navigate(ReferenceRoute) {
//                    popUpTo("registration") { inclusive = true }
//                }
            },
        )
        customerScreen(
            onCustomerClick = { customerId ->
                //navController.navigate("foodcard/$foodId")
            }
        )
    }

}