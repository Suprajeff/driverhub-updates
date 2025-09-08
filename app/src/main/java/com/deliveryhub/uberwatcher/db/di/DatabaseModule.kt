package com.deliveryhub.uberwatcher.db.di

import android.content.Context
import com.deliveryhub.uberwatcher.db.WatcherDatabase
import com.deliveryhub.uberwatcher.db.dao.NotificationDao
import com.deliveryhub.uberwatcher.db.client.RoomClient
import com.deliveryhub.uberwatcher.db.dao.DeliverooCustomerDao
import com.deliveryhub.uberwatcher.db.dao.DeliverooOrderDao
import com.deliveryhub.uberwatcher.db.dao.UberCustomerDao
import com.deliveryhub.uberwatcher.db.dao.UberOrderDao

internal class DatabaseModule private constructor(context: Context) {

    private val dbClient: WatcherDatabase by lazy {
        RoomClient(context).database
    }

    private val notificationDao: NotificationDao by lazy {
        dbClient.notificationDao()
    }

    private val deliverooOrderDao: DeliverooOrderDao by lazy {
        dbClient.deliverooOrderDao()
    }

    private val deliverooCustomerDao: DeliverooCustomerDao by lazy {
        dbClient.deliverooCustomerDao()
    }

    private val uberOrderDao: UberOrderDao by lazy {
        dbClient.uberOrderDao()
    }

    private val uberCustomerDao: UberCustomerDao by lazy {
        dbClient.uberCustomerDao()
    }

    fun getDAOs(): DAOHolder {
        return DAOHolder(
            notificationDao = notificationDao,
            deliverooOrderDao = deliverooOrderDao,
            deliverooCustomerDao = deliverooCustomerDao,
            uberOrderDao = uberOrderDao,
            uberCustomerDao = uberCustomerDao
        )
    }

    companion object {
        private var INSTANCE: DatabaseModule? = null

        fun getInstance(context: Context): DatabaseModule {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DatabaseModule(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

}