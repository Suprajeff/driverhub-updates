package com.deliveryhub.uberwatcher.data

import com.deliveryhub.uberwatcher.db.dao.DeliverooCustomerDao
import com.deliveryhub.uberwatcher.db.dao.UberCustomerDao
import com.deliveryhub.uberwatcher.db.models.asExternalModel
import com.deliveryhub.uberwatcher.models.types.deliveroo.DeliverooCustomer
import com.deliveryhub.uberwatcher.models.types.uber.UberCustomer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFirstCustomerRepository(
    private val deliverooCustomerDao: DeliverooCustomerDao,
    private val uberCustomerDao: UberCustomerDao
): CustomerRepository {

    override fun getDeliverooCustomers(): Flow<List<DeliverooCustomer>> =
        deliverooCustomerDao.getDeliverooCustomers().map { list -> list.map { it.asExternalModel() } }

    override fun getUberCustomers(): Flow<List<UberCustomer>> =
        uberCustomerDao.getUberCustomers().map { list -> list.map { it.asExternalModel() } }

}

//val customers by combine(
//    db.uberCustomerDao.getUberCustomers(),
//    db.deliverooCustomerDao.getDeliverooCustomers()
//) { uberList, deliverooList ->
//    val mappedUber = uberList.map { Customer.Uber(it.asExternalModel()) }
//    val mappedDeliveroo = deliverooList.map { Customer.Deliveroo(it.asExternalModel()) }
//
//    (mappedUber + mappedDeliveroo)
//        .sortedByDescending { it.timestamp }
//        .take(50) // newest 50 across both
//}.collectAsState(initial = emptyList())