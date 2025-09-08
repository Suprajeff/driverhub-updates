package com.deliveryhub.uberwatcher.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "deliverooOrders",
)

data class DeliverooOrderEntity(
    @PrimaryKey val id: String,
    val price: String?,
    val ordersCount: String?,
    val restaurantName: String?,
    val restaurantAddress: String?,
    val customerAddress: String?,
    val latitude: Double?,
    val longitude: Double?,
    val extraStatus: List<String>,
    val timestamp: Long,
)