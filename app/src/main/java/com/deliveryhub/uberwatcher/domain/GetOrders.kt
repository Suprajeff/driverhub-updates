package com.deliveryhub.uberwatcher.domain

import com.deliveryhub.uberwatcher.data.OrderRepository
import com.deliveryhub.uberwatcher.models.types.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetOrders(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(): Flow<List<Order>> =
        combine(
            orderRepository.getUberOrders(),
            orderRepository.getDeliverooOrders()
        ) { uberList, deliverooList ->

            val mappedUber = uberList.map { Order.Uber(it) }
            val mappedDeliveroo = deliverooList.map { Order.Deliveroo(it) }

            (mappedUber + mappedDeliveroo)
                .sortedByDescending { it.timestamp } // newest first
                .take(50) // newest 50 across both
        }
}