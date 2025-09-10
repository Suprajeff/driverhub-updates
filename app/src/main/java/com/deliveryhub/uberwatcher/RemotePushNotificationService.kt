package com.deliveryhub.uberwatcher

import android.util.Log
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooCustomer
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooOrder
import com.deliveryhub.uberwatcher.models.types.deliveroo.asEntity
import com.deliveryhub.uberwatcher.models.types.uber.UberCustomer
import com.deliveryhub.uberwatcher.models.types.uber.UberOrder
import com.deliveryhub.uberwatcher.models.types.uber.asEntity

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.ktor.client.call.body
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.forEach
import com.deliveryhub.uberwatcher.network.Result
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class RemotePushNotificationService: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Check if message has a type
        val updateType = remoteMessage.data["updateType"] ?: return

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val db = (applicationContext as WatcherApplication).daoHolders
                val client = (applicationContext as WatcherApplication).httpClient

                // 1️⃣ Decide which DAO and which last timestamp to use
                when (updateType) {
                    "new_uber_order" -> {
                        val lastTs = db.uberOrderDao.getLatestTimestamp() ?: 0L
                        val response: Result<List<UberOrder>> = client.post("https://n.xn--ida.top/sync_uber_orders") {
                            contentType(ContentType.Application.Json)
                            setBody(mapOf("since" to lastTs))
                        }.body()

                        when (response) {
                            is Result.Success -> {
                                response.data.forEach { db.uberOrderDao.insert(it.asEntity()) }
                            }
                            is Result.Error -> Log.e("FCM", "Server error: ${response.message}")
                            is Result.NotFound -> Log.d("FCM", "No new Uber orders")
                        }
                    }

                    "new_uber_customer" -> {
                        val lastTs = db.uberCustomerDao.getLatestTimestamp() ?: 0L
                        val response: Result<List<UberCustomer>> = client.post("https://n.xn--ida.top/sync_uber_customers") {
                            contentType(ContentType.Application.Json)
                            setBody(mapOf("since" to lastTs))
                        }.body()

                        when (response) {
                            is Result.Success -> {
                                response.data.forEach { db.uberCustomerDao.insert(it.asEntity()) }
                            }
                            is Result.Error -> Log.e("FCM", "Server error: ${response.message}")
                            is Result.NotFound -> Log.d("FCM", "No new Uber orders")
                        }

                    }

                    "new_deliveroo_order" -> {
                        val lastTs = db.deliverooOrderDao.getLatestTimestamp() ?: 0L
                        val response: Result<List<DeliverooOrder>> = client.post("https://n.xn--ida.top/sync/sync_deliveroo_orders") {
//                            parameter("since", lastTs)
                            contentType(ContentType.Application.Json)
                            setBody(mapOf("since" to lastTs))
                        }.body()

                        when (response) {
                            is Result.Success -> {
                                response.data.forEach { db.deliverooOrderDao.insert(it.asEntity()) }
                            }
                            is Result.Error -> Log.e("FCM", "Server error: ${response.message}")
                            is Result.NotFound -> Log.d("FCM", "No new Uber orders")
                        }

                    }

                    "new_deliveroo_customer" -> {
                        val lastTs = db.deliverooCustomerDao.getLatestTimestamp() ?: 0L
                        val response: Result<List<DeliverooCustomer>> = client.post("https://n.xn--ida.top/sync/sync_deliveroo_customers") {
                            contentType(ContentType.Application.Json)
                            setBody(mapOf("since" to lastTs))
                        }.body()

                        when (response) {
                            is Result.Success -> {
                                response.data.forEach { db.deliverooCustomerDao.insert(it.asEntity()) }
                            }
                            is Result.Error -> Log.e("FCM", "Server error: ${response.message}")
                            is Result.NotFound -> Log.d("FCM", "No new Uber orders")
                        }

                    }
                }

                Log.d("FCM", "Sync success for $updateType")
            } catch (e: Exception) {
                Log.e("FCM", "Sync error", e)
            }
        }

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // Send the new token server
        sendTokenToServer(token)
    }

    private fun sendTokenToServer(token: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val client = (applicationContext as WatcherApplication).httpClient
//                val response = client.post("https://n.xn--ida.top/register_new_token") {
//                    contentType(ContentType.Application.Json)
//                    setBody(token)
//                }
//                Log.d("FCM", "Synced successfully: ${response.status}")
            } catch (e: Exception) {
                Log.e("FCM", "Sync error", e)
            }
        }

    }

}