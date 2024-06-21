package com.cevdetkilickeser.emerchant.retrofit

import com.cevdetkilickeser.emerchant.data.entity.cart.Carts
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.product.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceDao {

    @GET("products")
    suspend fun getProducts(): Products

    @GET("products/categories")
    suspend fun getCategories(): List<Category>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): Products

    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): Products

    @GET("carts/user/{userId}")
    suspend fun getCarts(@Path("userId") userId: String): Carts

}
