package com.deliveryhub.uberwatcher.models.types.uber

import com.deliveryhub.uberwatcher.db.models.UberOrderEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UberOrder(
    @SerialName("_id") val id: String,
    @SerialName("_type") val type: String,            // "Delivery"
    @SerialName("_price") val price: String,           // "£4.59"
    @SerialName("_timeEstimation") val timeEstimation: Int,        // 22
    @SerialName("_distanceInMiles") val distanceInMiles: Double,   // 2.9
    @SerialName("_pickUp") val pickUp: String,  // "McDonald's® - Finchley Lido"
    @SerialName("_dropOff") val dropOff: String, // "5 Hendon Avenue, London, N3 1UL"
    @SerialName("_extraStatus") val extraStatus: List<String>, // e.g. ["Confirm_"]
    @SerialName("_timestamp") val timestamp: Long
)

fun UberOrder.asEntity() = UberOrderEntity(
    id = id,
    type = type,
    price = price,
    timeEstimation = timeEstimation,
    distanceInMiles = distanceInMiles,
    pickUp = pickUp,
    dropOff = dropOff,
    extraStatus = extraStatus,
    timestamp = timestamp
)