package com.deliveryhub.uberwatcher.models.types.deliveroo

import com.deliveryhub.uberwatcher.db.models.DeliverooOrderEntity
import kotlinx.serialization.Serializable

@Serializable
data class DeliverooOrder(
    val id: String,
    val price: String,
    val ordersCount: String,
    val restaurantName: String,
    val restaurantAddress: String,
    val customerAddress: String,
    val latitude: Double?,
    val longitude: Double?,
    val extraStatus: List<String>,
    val timestamp: Long
)

fun DeliverooOrder.asEntity() = DeliverooOrderEntity(
    id = id,
    price = price,
    ordersCount = ordersCount,
    restaurantName = restaurantName,
    restaurantAddress = restaurantAddress,
    customerAddress = customerAddress,
    latitude = latitude,
    longitude = longitude,
    extraStatus = extraStatus,
    timestamp = timestamp
)