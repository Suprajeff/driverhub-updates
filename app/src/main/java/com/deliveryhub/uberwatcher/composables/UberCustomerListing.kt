package com.deliveryhub.uberwatcher.composables

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.deliveryhub.uberwatcher.R
import com.deliveryhub.uberwatcher.models.types.uber.UberCustomer
import com.deliveryhub.uberwatcher.ui.theme.Typography

@Composable
fun UberCustomerListing(
    item: UberCustomer,
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
            ) {

                Image(
                    painter = painterResource(id = R.drawable.uber_circle),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp).padding(end = 8.dp)
                )

                Column {

                    Text(
                        text = item.customerName,
                        style = Typography.titleSmall
                    )


                    Text(
                        text = item.address,
                        style = Typography.bodySmall,
                        modifier = Modifier.clickable {
                            val gmmIntentUri =
                                "geo:0,0?q=${Uri.encode(item.address)}".toUri()
                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                                setPackage("com.google.android.apps.maps")
                            }
                            context.startActivity(mapIntent)
                        }.padding(vertical = 8.dp)
                    )
                }

            }
        }
    }
}