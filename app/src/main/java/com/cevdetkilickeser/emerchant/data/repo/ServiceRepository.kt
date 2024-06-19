package com.cevdetkilickeser.emerchant.data.repo

import com.cevdetkilickeser.emerchant.data.datasource.ServiceDataSource
import com.cevdetkilickeser.emerchant.data.entity.product.Product

class ServiceRepository(private val dataSource: ServiceDataSource) {
    suspend fun getProducts(): List<Product> = dataSource.getProducts()
}