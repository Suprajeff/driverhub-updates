package com.deliveryhub.uberwatcher.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.deliveryhub.uberwatcher.db.dao.DeliverooCustomerDao
import com.deliveryhub.uberwatcher.db.dao.DeliverooOrderDao
import com.deliveryhub.uberwatcher.db.dao.NotificationDao
import com.deliveryhub.uberwatcher.db.dao.UberCustomerDao
import com.deliveryhub.uberwatcher.db.dao.UberOrderDao
import com.deliveryhub.uberwatcher.db.models.DeliverooCustomerEntity
import com.deliveryhub.uberwatcher.db.models.DeliverooOrderEntity
import com.deliveryhub.uberwatcher.db.models.NotificationEntity
import com.deliveryhub.uberwatcher.db.models.UberCustomerEntity
import com.deliveryhub.uberwatcher.db.models.UberOrderEntity
import com.deliveryhub.uberwatcher.db.utils.PackageNameConverter
import com.deliveryhub.uberwatcher.db.utils.StringListConverter

@Database(
    entities = [
        NotificationEntity::class,
        DeliverooOrderEntity::class,
        DeliverooCustomerEntity::class,
        UberOrderEntity::class,
        UberCustomerEntity::class
    ],
    version = 2,
    exportSchema = true,
)
@TypeConverters(
    PackageNameConverter::class,
    StringListConverter::class
)
internal abstract class WatcherDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
    abstract fun deliverooOrderDao(): DeliverooOrderDao
    abstract fun deliverooCustomerDao(): DeliverooCustomerDao
    abstract fun uberOrderDao(): UberOrderDao
    abstract fun uberCustomerDao(): UberCustomerDao
}