package com.deliveryhub.uberwatcher

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.deliveryhub.uberwatcher.models.PackageName
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooNotificationType
import com.deliveryhub.uberwatcher.ui.theme.DriverTheme
import com.deliveryhub.uberwatcher.ui.theme.Typography
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.map
import androidx.core.net.toUri
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.Instant
import android.os.PowerManager
import androidx.compose.foundation.lazy.items
import com.deliveryhub.uberwatcher.db.di.DAOHolder


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val httpClient = (application as WatcherApp).httpClient

        val dao = (application as WatcherApp).daoHolders


        setContent {
            DriverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val context = LocalContext.current

                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        onEnableClick = {
                            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
                        },
                        onEnableAccessibility = {
                            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                        },
                        onEnableBatteryOptimisation = {
                            context.requestIgnoreBatteryOptimizations()
                        },
                        httpClient,
                        dao
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, onEnableClick: () -> Unit, onEnableAccessibility: () -> Unit, onEnableBatteryOptimisation: () -> Unit, client: HttpClient, db: DAOHolder) {

    val notifications by db.uberOrderDao.getUberOrders()
        .map { list ->
            list.sortedByDescending { it.timestamp } // newest first
                .take(50)                            // only keep 50
        }
        .collectAsState(initial = emptyList())

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
//        Button(
//            onClick = onEnableClick,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Enable Notification Access")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = onEnableAccessibility,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Enable Accessibility Service")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = onEnableBatteryOptimisation,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Enable Battery Optimisation")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))

//        Button(
//            onClick = {
//
//                val payload = NotificationItem(
//                    packageName = PackageName.UBER,
//                    title = "Uber request",
//                    text = "New Trip Received",
//                    subText = "Triggered manually",
//                    summaryText = "Button Click",
//                    category = "debug",
//                    timestamp = System.currentTimeMillis(),
//                    isOngoing = false,
//                    isClearable = true
//                )
//
//                CoroutineScope(Dispatchers.IO).launch {
//
//                    // Record to db
//
//                    try {
//                        db.notificationDao.insert(
//                            NotificationEntity(
//                                packageName = payload.packageName,
//                                title = payload.title,
//                                text = payload.text,
//                                subText = payload.subText,
//                                summaryText = payload.summaryText,
//                                category = payload.category,
//                                timestamp = payload.timestamp,
//                                isOngoing = payload.isOngoing,
//                                isClearable = payload.isClearable
//                            )
//                        )
//                    } catch (e: Exception) {
//                        Log.e("CourierHelper", "Error inserting notification to DB", e)
//                    }
//
//                    // Send to server
//
//                    try {
//
//                        val response = client.post("https://n.xn--ida.top/add_to_delivery_hub") {
//                            contentType(ContentType.Application.Json)
//                            setBody(payload)
//                        }
//                        Log.d("CourierHelper", "Sent test payload to server: ${response.status}")
//                    } catch (e: Exception) {
//                        Log.e("CourierHelper", "Error sending test payload", e)
//                    }
//
//                }
//
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Send Notification")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))

//        Text(
//            text = "Captured Notifications:",
//            style = Typography.titleMedium,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items( notifications) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ){

                            Image(
                                painter = painterResource(id = R.drawable.uber_circle),
                                contentDescription = null,
                                modifier = Modifier.size(50.dp).padding(end = 8.dp)
                            )

                        // Notification text
                        Column {

                            Text(
                                text = item.pickUp,
                                style = Typography.titleSmall
                            )


                            Text(
                                text = item.dropOff,
                                style = Typography.bodySmall,
                                modifier = Modifier.clickable {
                                    val gmmIntentUri =
                                        "geo:0,0?q=${Uri.encode(item.dropOff)}".toUri()
                                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                                        setPackage("com.google.android.apps.maps")
                                    }
                                    context.startActivity(mapIntent)
                                }.padding(vertical = 8.dp)
                            )


                            Text(
                                text = "Price: ${item.price} for ${item.distanceInMiles} (${item.timeEstimation} minutes)",
                                style = Typography.bodySmall
                            )


                            Text(
                                text = item.timestamp.let {
                                    DateTimeFormatter
                                        .ofPattern("EEE, dd MMM yy HH:mm")
                                        .withZone(ZoneId.systemDefault())
                                        .format(Instant.ofEpochMilli(it))
                                } ?: "unknown",
                                style = Typography.bodySmall
                            )

                        }
                        }

                        // Delete cross
                        IconButton(onClick = {
                            // Call your delete function here
                            CoroutineScope(Dispatchers.IO).launch {

                                try {
                                    db.notificationDao.delete(item.id)
                                } catch (e: Exception) {
                                    Log.e("CourierHelper", "Error inserting notification to DB", e)
                                }

                            }


                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Delete Notification"
                            )
                        }

                    }
                }
            }
        }
    }
}

fun Context.requestIgnoreBatteryOptimizations() {
    val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
    val packageName = packageName
    if (!pm.isIgnoringBatteryOptimizations(packageName)) {
        val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    DriverTheme {
//        MainScreen(onEnableClick = {}, )
//    }
//}