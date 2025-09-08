package com.deliveryhub.uberwatcher.db.client

import android.content.Context
import androidx.room.Room
import com.deliveryhub.uberwatcher.db.WatcherDatabase

class RoomClient(context: Context) {

    internal val database = Room.databaseBuilder(
        context,
        WatcherDatabase::class.java,
        "watcher-database"
    ).fallbackToDestructiveMigration(true).build()

}