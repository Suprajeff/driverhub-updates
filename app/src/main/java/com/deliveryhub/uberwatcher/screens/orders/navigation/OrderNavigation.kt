package com.deliveryhub.uberwatcher.screens.orders.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.deliveryhub.uberwatcher.screens.orders.OrdersRoute
import kotlinx.serialization.Serializable

@Serializable
object OrderRoute

fun NavController.navigateToOrder(navOptions: NavOptions) =
    navigate(route = OrderRoute, navOptions)

fun NavGraphBuilder.orderScreen(
    onOrderClick: (String) -> Unit
) {
    composable<OrderRoute> {
        OrdersRoute(onOrderClick)
    }
}