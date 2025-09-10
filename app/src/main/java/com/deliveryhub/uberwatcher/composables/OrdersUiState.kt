package com.deliveryhub.uberwatcher.composables

import com.deliveryhub.uberwatcher.models.types.Order

sealed interface OrdersUiState {
    data object Loading : OrdersUiState
    data class Success(val orders: List<Order>) : OrdersUiState
}
