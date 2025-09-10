package com.deliveryhub.uberwatcher.data

import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooOrder
import com.deliveryhub.uberwatcher.models.types.uber.UberOrder
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getDeliverooOrders(): Flow<List<DeliverooOrder>>
    fun getUberOrders(): Flow<List<UberOrder>>
}