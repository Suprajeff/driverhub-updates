package com.deliveryhub.uberwatcher.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.deliveryhub.uberwatcher.db.models.DeliverooCustomerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliverooCustomerDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(deliverooCustomer: DeliverooCustomerEntity)

    @Transaction
    @Query("""
        SELECT deliverooCustomers.*
        FROM deliverooCustomers
    """)
    fun getDeliverooCustomers(): Flow<List<DeliverooCustomerEntity>>

    @Query("DELETE FROM DeliverooCustomers WHERE id = :id")
    suspend fun delete(id: String)

    // Sync

    @Query("SELECT MAX(timestamp) FROM deliverooCustomers")
    suspend fun getLatestTimestamp(): Long?

}