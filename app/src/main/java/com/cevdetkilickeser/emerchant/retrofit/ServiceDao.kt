package com.cevdetkilickeser.emerchant.retrofit

import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.product.Products
import retrofit2.http.GET

interface ServiceDao {

    @GET("products")
    suspend fun getProducts(): Products
    @GET("products/categories")
    suspend fun getCategories(): List<Category>

}
