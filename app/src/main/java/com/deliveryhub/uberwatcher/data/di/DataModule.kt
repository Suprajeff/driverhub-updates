package com.deliveryhub.uberwatcher.data.di

import android.content.Context
import com.deliveryhub.uberwatcher.common.ConnectivityManagerNetworkMonitor
import com.deliveryhub.uberwatcher.common.DispatchersProvider
import com.deliveryhub.uberwatcher.data.CustomerRepository
import com.deliveryhub.uberwatcher.data.OrderRepository
import com.deliveryhub.uberwatcher.data.OfflineFirstCustomerRepository
import com.deliveryhub.uberwatcher.data.OfflineFirstOrderRepository
import com.deliveryhub.uberwatcher.db.di.DAOHolder
import com.deliveryhub.uberwatcher.data.util.NetworkMonitor
import kotlinx.coroutines.CoroutineDispatcher

object DataModule {

    private lateinit var applicationContext: Context

    private lateinit var ioDispatcher: CoroutineDispatcher

    private lateinit var daos: DAOHolder

    fun initialize(
        context: Context,
        ioDispatcher: CoroutineDispatcher = DispatchersProvider.provideIODispatcher(),
        daos: DAOHolder
    ): DataModule {
        this.applicationContext = context
        this.ioDispatcher = ioDispatcher
        this.daos = daos
        return this
    }

    val customerRepository: CustomerRepository by lazy {
        OfflineFirstCustomerRepository(daos.deliverooCustomerDao, daos.uberCustomerDao)
    }

    val orderRepository: OrderRepository by lazy {
        OfflineFirstOrderRepository(daos.deliverooOrderDao, daos.uberOrderDao)
    }

    val networkMonitor: NetworkMonitor by lazy {
        ConnectivityManagerNetworkMonitor(this.applicationContext, ioDispatcher)
    }

}