package com.deliveryhub.uberwatcher.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "uberOrders",
)

data class UberOrderEntity(
    @PrimaryKey val id: String,
    val type: String?,            // "Delivery"
    val price: String?,           // "£4.59"
    val timeEstimation: Int?,        // 22
    val distanceInMiles: Double?,   // 2.9
    val pickUp: String?,  // "McDonald's® - Finchley Lido"
    val dropOff: String?, // "5 Hendon Avenue, London, N3 1UL"
    val extraStatus: List<String>, // e.g. ["Confirm_"]
    val timestamp: Long
)