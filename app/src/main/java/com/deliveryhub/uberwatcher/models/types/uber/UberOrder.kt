package com.deliveryhub.uberwatcher.models.types.uber

import com.deliveryhub.uberwatcher.db.models.UberOrderEntity
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UberOrder(
    val id: String,
    val type: String?,            // "Delivery"
    val price: String?,           // "£4.59"
    val timeEstimation: Int?,        // 22
    val distanceInMiles: Double?,   // 2.9
    val pickUp: String?,  // "McDonald's® - Finchley Lido"
    val dropOff: String?, // "5 Hendon Avenue, London, N3 1UL"
    val extraStatus: List<String>, // e.g. ["Confirm_"]
    val timestamp: Long
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