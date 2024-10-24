package com.cevdetkilickeser.emerchant.data.datasource

import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.entity.cart.CartRequest
import com.cevdetkilickeser.emerchant.data.entity.cart.CartRequestProduct
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.entity.order.Order
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.data.entity.user.LoginRequest
import com.cevdetkilickeser.emerchant.data.entity.user.User
import com.cevdetkilickeser.emerchant.retrofit.ApiService
import com.cevdetkilickeser.emerchant.room.LikeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataSource(private val apiService: ApiService, private val likeDao: LikeDao) {

    suspend fun login(username: String, password: String): User? =
        withContext(Dispatchers.IO) {
            try {
                return@withContext apiService.login(LoginRequest(username, password))
            } catch (e: Exception) {
                return@withContext null
            }
        }

    suspend fun getAuthUserProfile(token: String): Profile =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getAuthUserProfile(token)
        }

    suspend fun updateProfile(userId: Int, updateProfileRequest: UpdateProfileRequest): Profile =
        withContext(Dispatchers.IO) {
            return@withContext apiService.updateProfile(
                userId, updateProfileRequest
            )
        }

    suspend fun getProducts(): List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getProducts().products
        }

    suspend fun getCategories(): List<Category> =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getCategories()
        }

    suspend fun getProductsByCategory(category: String): List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getProductsByCategory(category).products
        }

    suspend fun searchProducts(query: String): List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext apiService.searchProducts(query).products
        }

    suspend fun getOrders(userId: String): List<Order> =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getOrders(userId).orders
        }

    suspend fun getCart(userId: Int, cartRequestProducts: List<CartRequestProduct>): Cart =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getCart(CartRequest(userId, cartRequestProducts))
        }

    suspend fun getLikes(userId: String): List<Like> =
        withContext(Dispatchers.IO) {
            return@withContext likeDao.getLikes(userId)
        }

    suspend fun checkLike(userId: String, productId: Int): Like =
        withContext(Dispatchers.IO) {
            return@withContext likeDao.checkLike(userId, productId)
        }

    suspend fun insertLike(like: Like) {
        likeDao.insertLike(like)
    }

    suspend fun deleteLike(like: Like) {
        likeDao.deleteLike(like)
    }
}