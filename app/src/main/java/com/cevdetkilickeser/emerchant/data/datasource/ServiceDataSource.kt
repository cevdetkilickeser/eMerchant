package com.cevdetkilickeser.emerchant.data.datasource

import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.retrofit.ServiceDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ServiceDataSource(private val serviceDao: ServiceDao) {

    suspend fun getProducts() : List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.getProducts().products
        }

    suspend fun getCategories(): List<Category> =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.getCategories()
        }
}