package com.deliveryhub.uberwatcher.material.internal

internal fun interface FloatProducer {
    /** Returns the Float. */
    operator fun invoke(): Float
}