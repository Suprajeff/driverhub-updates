package com.deliveryhub.uberwatcher.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatchersProvider {
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}