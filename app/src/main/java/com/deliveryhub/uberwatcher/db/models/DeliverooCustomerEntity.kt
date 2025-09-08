package com.deliveryhub.uberwatcher.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "deliverooCustomers",
)

data class DeliverooCustomerEntity(
    @PrimaryKey val id: String,
    val customerName: String?,
    val customerAddress: String?,
    val latitude: Double?,
    val longitude: Double?,
    val distanceMeters: Int?,
    val timestamp: Long,
)