package com.deliveryhub.uberwatcher.models

import com.deliveryhub.uberwatcher.network.PackageNameSerializer
import kotlinx.serialization.Serializable

@Serializable
data class NotificationItem(
    @Serializable(with = PackageNameSerializer::class)
    val packageName: PackageName,
    // Optional values with sensible defaults
    val title: String = "",
    val text: String = "",
    val subText: String = "",
    val summaryText: String = "",
    val category: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val isOngoing: Boolean = false,
    val isClearable: Boolean = true
)