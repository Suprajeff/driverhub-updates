package com.deliveryhub.uberwatcher.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deliveryhub.uberwatcher.models.NotificationItem
import com.deliveryhub.uberwatcher.models.PackageName
import java.util.UUID

@Entity(
    tableName = "notifications",
)

data class NotificationEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
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

fun NotificationEntity.asExternalModel() = NotificationItem(
    packageName = packageName,
    title = title,
    text = text,
    subText = subText,
    summaryText = summaryText,
    category = category,
    timestamp = timestamp,
    isOngoing = isOngoing,
    isClearable = isClearable
)