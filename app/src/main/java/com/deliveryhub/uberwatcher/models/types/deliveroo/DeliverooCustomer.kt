package com.deliveryhub.uberwatcher.models.types.deliveroo

import com.deliveryhub.uberwatcher.db.models.DeliverooCustomerEntity
import kotlinx.serialization.Serializable

@Serializable
data class DeliverooCustomer(
    val id: String,
    val customerName: String?,
    val customerAddress: String?,
    val latitude: Double?,
    val longitude: Double?,
    val distanceMeters: Int?,
    val timestamp: Long
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