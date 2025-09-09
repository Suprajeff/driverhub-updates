package com.deliveryhub.uberwatcher.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooOrder

@Entity(
    tableName = "deliverooOrders",
)

data class DeliverooOrderEntity(
    @PrimaryKey val id: String,
    val price: String,
    val ordersCount: String,
    val restaurantName: String,
    val restaurantAddress: String,
    val customerAddress: String,
    val latitude: Double?,
    val longitude: Double?,
    val extraStatus: List<String>,
    val timestamp: Long,
)

fun DeliverooOrderEntity.asExternalModel() = DeliverooOrder(
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