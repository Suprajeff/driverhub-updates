package com.deliveryhub.uberwatcher.screens.customers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.deliveryhub.uberwatcher.WatcherApplication
import com.deliveryhub.uberwatcher.composables.CustomersUiState
import com.deliveryhub.uberwatcher.domain.GetCustomers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CustomersViewModel(
    private val savedStateHandle: SavedStateHandle,
    getCustomers: GetCustomers
): ViewModel() {

    val customersUiState: StateFlow<CustomersUiState> =
        getCustomers()
            .map { customers ->
                CustomersUiState.Success(customers)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CustomersUiState.Loading,
            )

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WatcherApplication

                val savedStateHandle = createSavedStateHandle()
                val customerRepository = application.dataModule.customerRepository
                CustomersViewModel(
                    savedStateHandle = savedStateHandle,
                    getCustomers = GetCustomers(customerRepository)
                )
            }
        }

    }

}