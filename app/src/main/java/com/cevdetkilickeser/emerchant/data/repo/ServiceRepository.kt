package com.cevdetkilickeser.emerchant.data.repo

import com.cevdetkilickeser.emerchant.data.datasource.ServiceDataSource
import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.user.User

class ServiceRepository(private val dataSource: ServiceDataSource) {

    suspend fun getProducts(): List<Product> = dataSource.getProducts()

    suspend fun getCategories(): List<Category> = dataSource.getCategories()

    suspend fun getProductsByCategory(category: String): List<Product> = dataSource.getProductsByCategory(category)

    suspend fun searchProducts(query: String): List<Product> = dataSource.searchProducts(query)

    suspend fun getCarts(userId: String): List<Cart> = dataSource.getCarts(userId)

    suspend fun getProfile(userId: String): Profile = dataSource.getProfile(userId)

    suspend fun getAuthUserProfile(token: String): Profile = dataSource.getAuthUserProfile(token)

    suspend fun login(username: String, password: String): User? =
        dataSource.login(username, password)
}