package com.deliveryhub.uberwatcher

import android.app.Application
import android.util.Log
import com.deliveryhub.uberwatcher.common.CoroutineScopesModule
import com.deliveryhub.uberwatcher.common.DispatchersProvider
import com.deliveryhub.uberwatcher.data.di.DataModule
import com.deliveryhub.uberwatcher.db.di.DAOHolder
import com.deliveryhub.uberwatcher.db.di.DatabaseModule
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooCustomer
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooOrder
import com.deliveryhub.uberwatcher.models.types.deliveroo.asEntity
import com.deliveryhub.uberwatcher.models.types.uber.UberCustomer
import com.deliveryhub.uberwatcher.models.types.uber.UberOrder
import com.deliveryhub.uberwatcher.models.types.uber.asEntity
import com.deliveryhub.uberwatcher.network.Result
import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.ContentType

import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.serialization.json.Json

class WatcherApplication : Application() {
    lateinit var httpClient: HttpClient
        private set

    lateinit var daoHolders: DAOHolder
        private set

    lateinit var ioDispatcher: CoroutineDispatcher
        private set

    lateinit var dataModule: DataModule
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

        ioDispatcher = DispatchersProvider.provideIODispatcher()
        val scope = CoroutineScopesModule.providesCoroutineScope(ioDispatcher)

        // Database

        // --- Room Database ---
        val dbModule = DatabaseModule.getInstance(applicationContext)
        daoHolders = dbModule.getDAOs()

        // Data Module
        dataModule = DataModule.initialize(
            this@WatcherApplication,
            ioDispatcher,
            daoHolders
        )

        // Notifications
        FirebaseMessaging.getInstance().subscribeToTopic("updates")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("DriverApp", "✅ Subscribed to 'updates' topic")
                } else {
                    Log.w("DriverApp", "❌ Failed to subscribe to 'updates' topic", task.exception)
                }
            }


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val db = (applicationContext as WatcherApplication).daoHolders
                val client = (applicationContext as WatcherApplication).httpClient

                db.uberCustomerDao.getUberCustomers().collect { customers ->
                    if (customers.isEmpty()) {

                        val response: Result<List<UberCustomer>> = client.get("https://n.xn--ida.top/get_uber_customers") {
                            contentType(ContentType.Application.Json)
                        }.body()

                        when (response) {
                            is Result.Success -> {
                                response.data.forEach { db.uberCustomerDao.insert(it.asEntity()) }
                            }
                            is Result.Error -> Log.e("FCM", "Server error: ${response.message}")
                            is Result.NotFound -> Log.d("FCM", "No new Uber customers")
                        }

                    }
                }

                db.deliverooCustomerDao.getDeliverooCustomers().collect { customers ->
                    if (customers.isEmpty()) {

                        val response: Result<List<DeliverooCustomer>> = client.get("https://n.xn--ida.top/get_deliveroo_customers") {
                            contentType(ContentType.Application.Json)
                        }.body()

                        when (response) {
                            is Result.Success -> {
                                response.data.forEach { db.deliverooCustomerDao.insert(it.asEntity()) }
                            }
                            is Result.Error -> Log.e("FCM", "Server error: ${response.message}")
                            is Result.NotFound -> Log.d("FCM", "No new Deliveroo customers")
                        }

                    }
                }

                db.deliverooOrderDao.getDeliverooOrders().collect { orders ->
                    if (orders.isEmpty()) {

                        val response: Result<List<DeliverooOrder>> = client.get("https://n.xn--ida.top/get_deliveroo_orders") {
                            contentType(ContentType.Application.Json)
                        }.body()

                        when (response) {
                            is Result.Success -> {
                                response.data.forEach { db.deliverooOrderDao.insert(it.asEntity()) }
                            }
                            is Result.Error -> Log.e("FCM", "Server error: ${response.message}")
                            is Result.NotFound -> Log.d("FCM", "No new Deliveroo orders")
                        }

                    }
                }

                db.uberOrderDao.getUberOrders().collect { orders ->
                    if (orders.isEmpty()) {

                        val response: Result<List<UberOrder>> = client.get("https://n.xn--ida.top/get_uber_orders") {
                            contentType(ContentType.Application.Json)
                        }.body()

                        when (response) {
                            is Result.Success -> {
                                response.data.forEach { db.uberOrderDao.insert(it.asEntity()) }
                            }
                            is Result.Error -> Log.e("FCM", "Server error: ${response.message}")
                            is Result.NotFound -> Log.d("FCM", "No new Uber orders")
                        }

                    }
                }

                Log.d("App", "Sync success")
            } catch (e: Exception) {
                Log.e("App", "Sync error", e)
            }
        }


    }

    override fun onTerminate() {
        super.onTerminate()
        httpClient.close()
    }
}
