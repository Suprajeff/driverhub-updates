package com.deliveryhub.uberwatcher.data

import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooCustomer
import com.deliveryhub.uberwatcher.models.types.uber.UberCustomer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getDeliverooCustomers(): Flow<List<DeliverooCustomer>>
    fun getUberCustomers(): Flow<List<UberCustomer>>
}