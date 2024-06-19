package com.cevdetkilickeser.emerchant.retrofit

import com.cevdetkilickeser.emerchant.data.entity.product.Products
import retrofit2.http.GET

interface ServiceDao {

    @GET("product")
    suspend fun getProducts(): Products

}
