package com.cevdetkilickeser.emerchant.data.datasource

import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.entity.cart.CartRequest
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.data.entity.user.LoginRequest
import com.cevdetkilickeser.emerchant.data.entity.user.User
import com.cevdetkilickeser.emerchant.retrofit.ServiceDao
import com.cevdetkilickeser.emerchant.room.LikeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataSource(private val serviceDao: ServiceDao, private val likeDao: LikeDao) {

    suspend fun login(username: String, password: String): User? =
        withContext(Dispatchers.IO) {
            try {
                return@withContext serviceDao.login(LoginRequest(username, password))
            } catch (e: Exception) {
                return@withContext null
            }
        }

    suspend fun getAuthUserProfile(token: String): Profile =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.getAuthUserProfile(token)
        }

    suspend fun updateProfile(userId: String, lastName: String): Int =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.updateProfile(
                userId.toInt(),
                UpdateProfileRequest(lastName)
            ).id
        }

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

    suspend fun addCart(userId: String, products: List<Product>): Int =
        withContext(Dispatchers.IO) {
            return@withContext serviceDao.addCart(CartRequest(userId.toInt(), products)).id
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