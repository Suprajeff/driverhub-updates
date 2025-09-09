package com.deliveryhub.uberwatcher.models.types

import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooOrder
import com.deliveryhub.uberwatcher.models.types.uber.UberOrder

sealed class Order {
    abstract val timestamp: Long

    data class Uber(val order: UberOrder) : Order() {
        override val timestamp get() = order.timestamp
    }

    data class Deliveroo(val order: DeliverooOrder) : Order() {
        override val timestamp get() = order.timestamp
    }
}