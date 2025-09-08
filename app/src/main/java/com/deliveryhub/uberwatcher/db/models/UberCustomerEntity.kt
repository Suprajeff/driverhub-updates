package com.deliveryhub.uberwatcher.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "uberCustomers",
)

data class UberCustomerEntity(
    @PrimaryKey val id: String,
    val customerName: String,
    val restaurantName: String,
    val orderNumber: String,
    val address: String,
    val timestamp: Long,
)