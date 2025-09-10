package com.deliveryhub.uberwatcher.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deliveryhub.uberwatcher.models.types.uber.UberCustomer

@Entity(
    tableName = "uberCustomers",
)

data class UberCustomerEntity(
    @PrimaryKey val id: String,
    val customerName: String,
    val dob: String?,
    val lastFour: String?,
    val restaurantName: String,
    val orderNumber: String,
    val address: String,
    val timestamp: Long,
)

fun UberCustomerEntity.asExternalModel() = UberCustomer(
    id = id,
    customerName = customerName,
    dob = dob,
    lastFour = lastFour,
    restaurantName = restaurantName,
    orderNumber = orderNumber,
    address = address,
    timestamp = timestamp
)