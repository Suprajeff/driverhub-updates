package com.deliveryhub.uberwatcher.db.di

import android.content.Context

object DatabaseLocator {
    private var databaseModule: DatabaseModule? = null

    fun initialize(context: Context) {
        if (databaseModule == null) {
            databaseModule = DatabaseModule.getInstance(context)
        }
    }

    fun getDAOHolder(): DAOHolder {
        return databaseModule?.getDAOs() ?: throw IllegalStateException("Database Locator not initialized.")
    }
}