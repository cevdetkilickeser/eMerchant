package com.cevdetkilickeser.emerchant.data.repo

import com.cevdetkilickeser.emerchant.data.datasource.DataSource
import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.user.User

class Repository(private val dataSource: DataSource) {

    suspend fun login(username: String, password: String): User? =
        dataSource.login(username, password)

    suspend fun getAuthUserProfile(token: String): Profile =
        dataSource.getAuthUserProfile(token)

    suspend fun updateProfile(userId: String, lastName: String): Int =
        dataSource.updateProfile(userId, lastName)

    suspend fun getProducts(): List<Product> =
        dataSource.getProducts()

    suspend fun getCategories(): List<Category> =
        dataSource.getCategories()

    suspend fun getProductsByCategory(category: String): List<Product> =
        dataSource.getProductsByCategory(category)

    suspend fun searchProducts(query: String): List<Product> =
        dataSource.searchProducts(query)

    suspend fun getCarts(userId: String): List<Cart> =
        dataSource.getCarts(userId)

    suspend fun addCart(userId: String, products: List<Product>): Int =
        dataSource.addCart(userId, products)

    suspend fun getLikes(userId: String): List<Like> =
        dataSource.getLikes(userId)

    suspend fun checkLike(userId: String, productId: Int): Like =
        dataSource.checkLike(userId, productId)

    suspend fun insertLike(like: Like) =
        dataSource.insertLike(like)

    suspend fun deleteLike(like: Like) =
        dataSource.deleteLike(like)

}