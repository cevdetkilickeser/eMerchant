package com.cevdetkilickeser.emerchant.data.datasource

import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.retrofit.ServiceDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ServiceDataSource(private val serviceDao: ServiceDao) {

    suspend fun getProducts() : List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.getProducts().products
        }
}