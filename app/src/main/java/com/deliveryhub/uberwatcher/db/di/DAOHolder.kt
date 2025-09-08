package com.deliveryhub.uberwatcher.db.di

import com.deliveryhub.uberwatcher.db.dao.DeliverooCustomerDao
import com.deliveryhub.uberwatcher.db.dao.DeliverooOrderDao
import com.deliveryhub.uberwatcher.db.dao.NotificationDao
import com.deliveryhub.uberwatcher.db.dao.UberCustomerDao
import com.deliveryhub.uberwatcher.db.dao.UberOrderDao

data class DAOHolder (
    val notificationDao: NotificationDao,
    val deliverooOrderDao: DeliverooOrderDao,
    val deliverooCustomerDao: DeliverooCustomerDao,
    val uberOrderDao: UberOrderDao,
    val uberCustomerDao: UberCustomerDao
)