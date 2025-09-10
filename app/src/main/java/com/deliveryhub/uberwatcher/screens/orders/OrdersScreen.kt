package com.deliveryhub.uberwatcher.screens.orders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deliveryhub.uberwatcher.composables.DeliverooOrderListing
import com.deliveryhub.uberwatcher.composables.OrdersUiState
import com.deliveryhub.uberwatcher.composables.UberOrderListing
import com.deliveryhub.uberwatcher.models.types.Order

@Composable
internal fun OrdersRoute(
    onOrderClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrdersViewModel = viewModel(factory = OrdersViewModel.Factory)
) {
    val uiState by viewModel.ordersUiState.collectAsState()

    OrdersScreen(
        uiState = uiState,
//        onDeleteUber = { uberOrder ->
//            viewModel.deleteUberOrder(uberOrder)   // implement in VM
//        },
//        onDeleteDeliveroo = { deliverooOrder ->
//            viewModel.deleteDeliverooOrder(deliverooOrder) // implement in VM
//        },
        onOrderClick = onOrderClick,
        modifier = modifier
    )
}

@Composable
fun OrdersScreen(
    uiState: OrdersUiState,
//    onDeleteUber: (UberOrder) -> Unit,
//    onDeleteDeliveroo: (DeliverooOrder) -> Unit,
    onOrderClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        OrdersUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is OrdersUiState.Success -> {
            val context = LocalContext.current
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(
                    items = uiState.orders,
                    key = { order ->
                        when (order) {
                            is Order.Uber -> "uber-${order.order.id}"
                            is Order.Deliveroo -> "deliveroo-${order.order.id}"
                        }
                    }
                ) { order ->
                    when (order) {
                        is Order.Uber -> UberOrderListing(
                            item = order.order,
                            context = context,
                            // onDelete = { onDeleteUber(order.order) }
                        )
                        is Order.Deliveroo -> DeliverooOrderListing(
                            item = order.order,
                            context = context,
                            // onDelete = { onDeleteDeliveroo(order.order) }
                        )
                    }

                    // ✅ Example usage of onOrderClick
                    // Call this when you want to navigate, e.g. after tapping a row
                    // onOrderClick(order.id.toString())
                }
            }
        }
    }
}

