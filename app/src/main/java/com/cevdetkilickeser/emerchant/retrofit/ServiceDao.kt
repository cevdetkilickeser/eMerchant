package com.cevdetkilickeser.emerchant.retrofit

import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.entity.cart.CartRequest
import com.cevdetkilickeser.emerchant.data.entity.cart.Carts
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.product.Products
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.data.entity.user.LoginRequest
import com.cevdetkilickeser.emerchant.data.entity.user.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceDao {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): User?

    @GET("auth/me")
    suspend fun getAuthUserProfile(@Header("Authorization") token: String): Profile

    @Headers("Content-Type: application/json")
    @PUT("users/{id}")
    fun updateProfile(@Path("id") id: Int, @Body updateUserRequest: UpdateProfileRequest): Profile

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

    @Headers("Content-Type: application/json")
    @POST("carts/add")
    suspend fun addCart(@Body cartRequest: CartRequest): Cart

}

