package com.deliveryhub.uberwatcher.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
sealed class Result<out T> {
    @Serializable
    @SerialName("success")
    data class Success<T>(
        val status: String,
        val data: T
    ) : Result<T>()

    @Serializable
    @SerialName("error")
    data class Error(
        val status: String,
        val message: String
    ) : Result<Nothing>()

    @Serializable
    @SerialName("notFound")
    data class NotFound(
        val status: String
    ) : Result<Nothing>()
}
