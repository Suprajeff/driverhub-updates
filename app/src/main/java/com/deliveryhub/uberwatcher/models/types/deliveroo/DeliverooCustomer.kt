package com.deliveryhub.uberwatcher.models.types.deliveroo

import com.deliveryhub.uberwatcher.db.models.DeliverooCustomerEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeliverooCustomer(
    @SerialName("_id") val id: String,
    @SerialName("_customerName") val customerName: String,
    @SerialName("_customerAddress") val customerAddress: String,
    @SerialName("_latitude") val latitude: Double?,
    @SerialName("_longitude") val longitude: Double?,
    @SerialName("_distanceMeters") val distanceMeters: Int?,
    @SerialName("_timestamp") val timestamp: Long
)

fun DeliverooCustomer.asEntity() = DeliverooCustomerEntity(
    id = id,
    customerName = customerName,
    customerAddress = customerAddress,
    latitude = latitude,
    longitude = longitude,
    distanceMeters = distanceMeters,
    timestamp = timestamp
)