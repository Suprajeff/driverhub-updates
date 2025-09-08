package com.deliveryhub.uberwatcher.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.deliveryhub.uberwatcher.db.models.DeliverooOrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliverooOrderDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(deliverooOrder: DeliverooOrderEntity)

    @Transaction
    @Query("""
        SELECT deliverooOrders.*
        FROM deliverooOrders
    """)
    fun getDeliverooOrders(): Flow<List<DeliverooOrderEntity>>

    @Query("DELETE FROM DeliverooOrders WHERE id = :id")
    suspend fun delete(id: String)

    // Sync

    @Query("SELECT MAX(timestamp) FROM deliverooOrders")
    suspend fun getLatestTimestamp(): Long?

}