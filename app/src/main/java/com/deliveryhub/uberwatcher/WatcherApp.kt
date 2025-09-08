package com.deliveryhub.uberwatcher

import android.app.Application
import android.util.Log
import com.deliveryhub.uberwatcher.db.di.DAOHolder
import com.deliveryhub.uberwatcher.db.di.DatabaseModule
import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType

import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

import kotlinx.serialization.json.Json

class WatcherApp : Application() {
    lateinit var httpClient: HttpClient
        private set

    lateinit var daoHolders: DAOHolder
        private set


    override fun onCreate() {
        super.onCreate()

        val json: Json = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }

        httpClient = HttpClient(OkHttp) {
            // configure engine, logging, timeouts, etc.
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }

            install(ContentNegotiation) {
                json(json)
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
            }

        }

        // Database

        // --- Room Database ---
        val dbModule = DatabaseModule.getInstance(applicationContext)
        daoHolders = dbModule.getDAOs()

        // Notifications
        FirebaseMessaging.getInstance().subscribeToTopic("updates")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("DriverApp", "✅ Subscribed to 'updates' topic")
                } else {
                    Log.w("DriverApp", "❌ Failed to subscribe to 'updates' topic", task.exception)
                }
            }


    }

    override fun onTerminate() {
        super.onTerminate()
        httpClient.close()
    }
}
