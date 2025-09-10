package com.deliveryhub.uberwatcher.data

import com.deliveryhub.uberwatcher.db.dao.DeliverooOrderDao
import com.deliveryhub.uberwatcher.db.dao.UberOrderDao
import com.deliveryhub.uberwatcher.db.models.DeliverooOrderEntity
import com.deliveryhub.uberwatcher.db.models.asExternalModel
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooOrder
import com.deliveryhub.uberwatcher.models.types.uber.UberOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFirstOrderRepository(
    private val deliverooOrderDao: DeliverooOrderDao,
    private val uberOrderDao: UberOrderDao
): OrderRepository {

    override fun getDeliverooOrders(): Flow<List<DeliverooOrder>> =
        deliverooOrderDao.getDeliverooOrders().map { list -> list.map { it.asExternalModel() } }

    override fun getUberOrders(): Flow<List<UberOrder>> =
        uberOrderDao.getUberOrders().map { list -> list.map { it.asExternalModel() } }

}