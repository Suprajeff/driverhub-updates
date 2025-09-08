package com.deliveryhub.uberwatcher.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.deliveryhub.uberwatcher.db.models.NotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(Notification: NotificationEntity)

    @Transaction
    @Query("""
        SELECT notifications.*
        FROM notifications
    """)
    fun getNotifications(): Flow<List<NotificationEntity>>


    @Query("SELECT * FROM notifications WHERE isOngoing = 0 ORDER BY timestamp DESC")
    fun getNonOngoingNotifications(): Flow<List<NotificationEntity>>


    @Transaction
    @Query("""
    SELECT *
    FROM notifications
    WHERE packageName IN (:packages)
    ORDER BY timestamp DESC
""")
    fun getNotificationsByPlatform(packages: List<String>): Flow<List<NotificationEntity>>


    @Query("DELETE FROM Notifications WHERE id = :id")
    suspend fun delete(id: String)

}