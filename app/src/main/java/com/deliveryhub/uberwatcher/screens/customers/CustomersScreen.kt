package com.deliveryhub.uberwatcher.screens.customers

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
import com.deliveryhub.uberwatcher.composables.CustomersUiState
import com.deliveryhub.uberwatcher.composables.DeliverooCustomerListing
import com.deliveryhub.uberwatcher.composables.UberCustomerListing
import com.deliveryhub.uberwatcher.models.types.Customer

@Composable
internal fun CustomersRoute(
    onCustomerClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CustomersViewModel = viewModel(factory = CustomersViewModel.Factory)
) {
    val uiState by viewModel.customersUiState.collectAsState()

    CustomersScreen(
        uiState = uiState,
//        onDeleteUber = { uberCustomer ->
//            viewModel.deleteUberCustomer(uberCustomer)   // implement in VM
//        },
//        onDeleteDeliveroo = { deliverooCustomer ->
//            viewModel.deleteDeliverooCustomer(deliverooCustomer) // implement in VM
//        },
        onCustomerClick = onCustomerClick,
        modifier = modifier
    )
}

@Composable
fun CustomersScreen(
    uiState: CustomersUiState,
//    onDeleteUber: (UberCustomer) -> Unit,
//    onDeleteDeliveroo: (DeliverooCustomer) -> Unit,
    onCustomerClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        CustomersUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is CustomersUiState.Success -> {
            val context = LocalContext.current
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(
                    items = uiState.customers,
                    key = { customer ->
                        when (customer) {
                            is Customer.Uber -> "uber-${customer.customer.id}"
                            is Customer.Deliveroo -> "deliveroo-${customer.customer.id}"
                        }
                    }
                ) { customer ->
                    when (customer) {
                        is Customer.Uber -> UberCustomerListing(
                            item = customer.customer,
                            context = context,
                            // onDelete = { onDeleteUber(customer.customer) }
                        )
                        is Customer.Deliveroo -> DeliverooCustomerListing(
                            item = customer.customer,
                            context = context,
                            // onDelete = { onDeleteDeliveroo(customer.customer) }
                        )
                    }

                    // ✅ Example usage of onCustomerClick
                    // Call this when you want to navigate, e.g. after tapping a row
                    // onCustomerClick(customer.id.toString())
                }
            }
        }
    }
}