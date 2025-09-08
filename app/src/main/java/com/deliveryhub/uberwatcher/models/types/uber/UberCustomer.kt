package com.deliveryhub.uberwatcher.models.types.uber

import com.deliveryhub.uberwatcher.db.models.UberCustomerEntity
import kotlinx.serialization.Serializable

@Serializable
data class UberCustomer(
    val id: String,
    val customerName: String,
    val restaurantName: String,
    val orderNumber: String,
    val address: String,
    val timestamp: Long
)

fun UberCustomer.asEntity() = UberCustomerEntity(
    id = id,
    customerName = customerName,
    restaurantName = restaurantName,
    orderNumber = orderNumber,
    address = address,
    timestamp = timestamp
)