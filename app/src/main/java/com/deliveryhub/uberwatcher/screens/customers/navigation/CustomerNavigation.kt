package com.deliveryhub.uberwatcher.screens.customers.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.deliveryhub.uberwatcher.screens.customers.CustomersRoute
import kotlinx.serialization.Serializable

@Serializable
object CustomerRoute

fun NavController.navigateToCustomer(navOptions: NavOptions) =
    navigate(route = CustomerRoute, navOptions)

fun NavGraphBuilder.customerScreen(
    onCustomerClick: (String) -> Unit
) {
    composable<CustomerRoute> {
        CustomersRoute(onCustomerClick)
    }
}