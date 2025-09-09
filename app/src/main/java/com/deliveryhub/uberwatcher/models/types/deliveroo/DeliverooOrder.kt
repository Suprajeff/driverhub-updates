package com.deliveryhub.uberwatcher.models.types.deliveroo

import com.deliveryhub.uberwatcher.db.models.DeliverooOrderEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeliverooOrder(
    @SerialName("_id") val id: String,
    @SerialName("_price") val price: String,
    @SerialName("_ordersCount") val ordersCount: String,
    @SerialName("_restaurantName") val restaurantName: String,
    @SerialName("_restaurantAddress") val restaurantAddress: String,
    @SerialName("_customerAddress") val customerAddress: String,
    @SerialName("_latitude") val latitude: Double?,
    @SerialName("_longitude") val longitude: Double?,
    @SerialName("_extraStatus") val extraStatus: List<String>,
    @SerialName("_timestamp") val timestamp: Long
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