package com.deliveryhub.uberwatcher.material.internal

import kotlinx.coroutines.CancellationException

/**
 * Represents a platform-optimized cancellation exception. This allows us to configure exceptions
 * separately on JVM and other platforms.
 */
internal abstract class PlatformOptimizedCancellationException(message: String? = null) :
    CancellationException(message) {
    // No specific JVM optimizations are typically added here unless needed.
    // For example, if you wanted to add a specific cause constructor or custom properties,
    // you would do that here.
}