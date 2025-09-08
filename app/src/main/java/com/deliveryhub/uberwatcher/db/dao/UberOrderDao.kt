package com.deliveryhub.uberwatcher.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.deliveryhub.uberwatcher.db.models.UberOrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UberOrderDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(uberOrder: UberOrderEntity)

    @Transaction
    @Query("""
        SELECT uberOrders.*
        FROM uberOrders
    """)
    fun getUberOrders(): Flow<List<UberOrderEntity>>

    @Query("DELETE FROM UberOrders WHERE id = :id")
    suspend fun delete(id: String)

    // Sync

    @Query("SELECT MAX(timestamp) FROM uberOrders")
    suspend fun getLatestTimestamp(): Long?

}
