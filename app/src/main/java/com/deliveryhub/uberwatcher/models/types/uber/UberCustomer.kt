package com.deliveryhub.uberwatcher.models.types.uber

import com.deliveryhub.uberwatcher.db.models.UberCustomerEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UberCustomer(
    @SerialName("_id") val id: String,
    @SerialName("_customerName") val customerName: String,
    @SerialName("_restaurantName") val restaurantName: String,
    @SerialName("_orderNumber") val orderNumber: String,
    @SerialName("_address") val address: String,
    @SerialName("_timestamp") val timestamp: Long
)

fun UberCustomer.asEntity() = UberCustomerEntity(
    id = id,
    customerName = customerName,
    restaurantName = restaurantName,
    orderNumber = orderNumber,
    address = address,
    timestamp = timestamp
)