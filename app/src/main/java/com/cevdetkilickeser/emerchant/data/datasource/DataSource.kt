package com.cevdetkilickeser.emerchant.data.datasource

import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.entity.cart.CartRequestProduct
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.entity.order.Order
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.data.entity.user.User

interface DataSource {

    suspend fun login(username: String, password: String): User?

    suspend fun getAuthUserProfile(token: String): Profile

    suspend fun updateProfile(userId: Int, updateProfileRequest: UpdateProfileRequest): Profile

    suspend fun getProducts(): List<Product>

    suspend fun getCategories(): List<Category>

    suspend fun getProductsByCategory(category: String): List<Product>

    suspend fun searchProducts(query: String): List<Product>

    suspend fun getOrders(userId: String): List<Order>

    suspend fun getCart(userId: Int, cartRequestProducts: List<CartRequestProduct>): Cart

    suspend fun getLikes(userId: String): List<Like>

    suspend fun checkLike(userId: String, productId: Int): Like

    suspend fun insertLike(like: Like)

    suspend fun deleteLike(like: Like)
}