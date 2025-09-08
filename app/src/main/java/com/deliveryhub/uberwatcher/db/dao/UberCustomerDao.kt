package com.deliveryhub.uberwatcher.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.deliveryhub.uberwatcher.db.models.UberCustomerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UberCustomerDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(uberCustomer: UberCustomerEntity)

    @Transaction
    @Query("""
        SELECT uberCustomers.*
        FROM uberCustomers
    """)
    fun getUberCustomers(): Flow<List<UberCustomerEntity>>

    @Query("DELETE FROM UberCustomers WHERE id = :id")
    suspend fun delete(id: String)

    // Sync

    @Query("SELECT MAX(timestamp) FROM uberCustomers")
    suspend fun getLatestTimestamp(): Long?

}