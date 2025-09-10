package com.deliveryhub.uberwatcher.models.types

import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooCustomer
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooOrder
import com.deliveryhub.uberwatcher.models.types.uber.UberCustomer
import com.deliveryhub.uberwatcher.models.types.uber.UberOrder

sealed class Customer {
    abstract val timestamp: Long

    data class Uber(val customer: UberCustomer) : Customer() {
        override val timestamp get() = customer.timestamp
    }

    data class Deliveroo(val customer: DeliverooCustomer) : Customer() {
        override val timestamp get() = customer.timestamp
    }
}