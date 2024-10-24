package com.cevdetkilickeser.emerchant.data.datasource

import com.cevdetkilickeser.emerchant.data.model.cart.Cart
import com.cevdetkilickeser.emerchant.data.model.cart.CartRequest
import com.cevdetkilickeser.emerchant.data.model.cart.CartRequestProduct
import com.cevdetkilickeser.emerchant.data.model.category.Category
import com.cevdetkilickeser.emerchant.data.model.like.Like
import com.cevdetkilickeser.emerchant.data.model.order.Order
import com.cevdetkilickeser.emerchant.data.model.product.Product
import com.cevdetkilickeser.emerchant.data.model.profile.Profile
import com.cevdetkilickeser.emerchant.data.model.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.data.model.user.LoginRequest
import com.cevdetkilickeser.emerchant.data.model.user.User
import com.cevdetkilickeser.emerchant.retrofit.ApiService
import com.cevdetkilickeser.emerchant.room.LikeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val likeDao: LikeDao
) : DataSource {

    override suspend fun login(username: String, password: String): User? =
        withContext(Dispatchers.IO) {
            try {
                return@withContext apiService.login(LoginRequest(username, password))
            } catch (e: Exception) {
                return@withContext null
            }
        }

    override suspend fun getAuthUserProfile(token: String): Profile =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getAuthUserProfile(token)
        }

    override suspend fun updateProfile(
        userId: Int,
        updateProfileRequest: UpdateProfileRequest
    ): Profile =
        withContext(Dispatchers.IO) {
            return@withContext apiService.updateProfile(
                userId, updateProfileRequest
            )
        }

    override suspend fun getProducts(): List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getProducts().products
        }

    override suspend fun getCategories(): List<Category> =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getCategories()
        }

    override suspend fun getProductsByCategory(category: String): List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getProductsByCategory(category).products
        }

    override suspend fun searchProducts(query: String): List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext apiService.searchProducts(query).products
        }

    override suspend fun getOrders(userId: String): List<Order> =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getOrders(userId).orders
        }

    override suspend fun getCart(userId: Int, cartRequestProducts: List<CartRequestProduct>): Cart =
        withContext(Dispatchers.IO) {
            return@withContext apiService.getCart(CartRequest(userId, cartRequestProducts))
        }

    override suspend fun getLikes(userId: String): List<Like> =
        withContext(Dispatchers.IO) {
            return@withContext likeDao.getLikes(userId)
        }

    override suspend fun checkLike(userId: String, productId: Int): Like =
        withContext(Dispatchers.IO) {
            return@withContext likeDao.checkLike(userId, productId)
        }

    override suspend fun insertLike(like: Like) {
        likeDao.insertLike(like)
    }

    override suspend fun deleteLike(like: Like) {
        likeDao.deleteLike(like)
    }
}