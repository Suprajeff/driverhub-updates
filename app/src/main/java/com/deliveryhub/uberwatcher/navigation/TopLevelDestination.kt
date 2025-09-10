package com.deliveryhub.uberwatcher.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.deliveryhub.uberwatcher.screens.customers.navigation.CustomerRoute
import com.deliveryhub.uberwatcher.screens.orders.navigation.OrderRoute
import kotlin.reflect.KClass
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import com.deliveryhub.uberwatcher.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
) {
    ORDERS(
        selectedIcon = Icons.AutoMirrored.Filled.List,
        unselectedIcon = Icons.AutoMirrored.Outlined.List,
        iconTextId = R.string.orders_title,
        titleTextId = R.string.orders_title,
        route = OrderRoute::class
    ),
    CUSTOMERS(
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        iconTextId = R.string.customers_title,
        titleTextId = R.string.customers_title,
        route = CustomerRoute::class
    )
}