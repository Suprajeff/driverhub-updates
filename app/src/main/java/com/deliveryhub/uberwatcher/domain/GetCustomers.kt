package com.deliveryhub.uberwatcher.domain

import com.deliveryhub.uberwatcher.data.CustomerRepository
import com.deliveryhub.uberwatcher.models.types.Customer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetCustomers(
    private val customerRepository: CustomerRepository
) {
    operator fun invoke(): Flow<List<Customer>> =
        combine(
            customerRepository.getUberCustomers(),
            customerRepository.getDeliverooCustomers()
        ) { uberList, deliverooList ->

            val mappedUber = uberList.map { Customer.Uber(it) }
            val mappedDeliveroo = deliverooList.map { Customer.Deliveroo(it) }

            (mappedUber + mappedDeliveroo)
                .sortedByDescending { it.timestamp } // newest first
                .take(50) // newest 50 across both
        }
}