package com.deliveryhub.uberwatcher.composables

import com.deliveryhub.uberwatcher.models.types.Customer

sealed interface CustomersUiState {
    data object Loading : CustomersUiState
    data class Success(val customers: List<Customer>) : CustomersUiState
}