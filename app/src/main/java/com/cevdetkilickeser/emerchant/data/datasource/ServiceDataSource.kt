package com.cevdetkilickeser.emerchant.data.datasource

import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.user.LoginRequest
import com.cevdetkilickeser.emerchant.data.entity.user.User
import com.cevdetkilickeser.emerchant.retrofit.ServiceDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ServiceDataSource(private val serviceDao: ServiceDao) {

    suspend fun getProducts(): List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.getProducts().products
        }

    suspend fun getCategories(): List<Category> =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.getCategories()
        }

    suspend fun getProductsByCategory(category: String): List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.getProductsByCategory(category).products
        }

    suspend fun searchProducts(query: String): List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.searchProducts(query).products
        }

    suspend fun getCarts(userId: String): List<Cart> =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.getCarts(userId).carts
        }

    suspend fun getProfile(userId: String): Profile =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.getProfile(userId)
        }

    suspend fun getAuthUserProfile(token: String): Profile =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.getAuthUserProfile(token)
        }

    suspend fun login(username: String, password: String): User? =
        withContext(Dispatchers.IO) {
            try {
                return@withContext serviceDao.login(LoginRequest(username, password))
            } catch (e: Exception) {
                return@withContext null
            }
        }
}