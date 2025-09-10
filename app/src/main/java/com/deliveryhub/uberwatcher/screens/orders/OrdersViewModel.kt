package com.deliveryhub.uberwatcher.screens.orders

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.deliveryhub.uberwatcher.WatcherApplication
import com.deliveryhub.uberwatcher.composables.OrdersUiState
import com.deliveryhub.uberwatcher.domain.GetOrders
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class OrdersViewModel(
    private val savedStateHandle: SavedStateHandle,
    getOrders: GetOrders
): ViewModel() {

    val ordersUiState: StateFlow<OrdersUiState> =
        getOrders()
            .map { orders ->
                OrdersUiState.Success(orders)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = OrdersUiState.Loading,
            )

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WatcherApplication

                val savedStateHandle = createSavedStateHandle()
                val orderRepository = application.dataModule.orderRepository
                OrdersViewModel(
                    savedStateHandle = savedStateHandle,
                    getOrders = GetOrders(orderRepository)
                )
            }
        }

    }

}