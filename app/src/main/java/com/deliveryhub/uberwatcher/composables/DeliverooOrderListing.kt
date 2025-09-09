package com.deliveryhub.uberwatcher.composables

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.deliveryhub.uberwatcher.R
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooOrder
import com.deliveryhub.uberwatcher.models.types.uber.UberOrder
import com.deliveryhub.uberwatcher.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun DeliverooOrderListing(
    item: DeliverooOrder,
    context: Context
) {

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
                    painter = painterResource(id = R.drawable.deliveroo_circle),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp).padding(end = 8.dp)
                )

                // Notification text
                Column {

                    Text(
                        text = "${item.restaurantName} (${item.restaurantAddress})",
                        style = Typography.titleSmall
                    )


                    Text(
                        text = item.customerAddress,
                        style = Typography.bodySmall,
                        modifier = Modifier.clickable {
                            val gmmIntentUri =
                                "geo:0,0?q=${Uri.encode(item.customerAddress)}".toUri()
                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                                setPackage("com.google.android.apps.maps")
                            }
                            context.startActivity(mapIntent)
                        }.padding(vertical = 8.dp)
                    )


                    Text(
                        text = item.price,
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