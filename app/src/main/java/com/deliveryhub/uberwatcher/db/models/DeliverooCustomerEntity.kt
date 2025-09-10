package com.deliveryhub.uberwatcher.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooCustomer

@Entity(
    tableName = "deliverooCustomers",
)

data class DeliverooCustomerEntity(
    @PrimaryKey val id: String,
    val customerName: String,
    val customerAddress: String,
    val dob: String?,
    val lastTwo: String?,
    val latitude: Double?,
    val longitude: Double?,
    val distanceMeters: Int?,
    val timestamp: Long,
)

fun DeliverooCustomerEntity.asExternalModel() = DeliverooCustomer(
    id = id,
    customerName = customerName,
    customerAddress = customerAddress,
    dob = dob,
    lastTwo = lastTwo,
    latitude = latitude,
    longitude = longitude,
    distanceMeters = distanceMeters,
    timestamp = timestamp
)