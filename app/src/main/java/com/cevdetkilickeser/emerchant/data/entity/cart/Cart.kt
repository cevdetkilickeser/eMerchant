package com.cevdetkilickeser.emerchant.data.entity.cart


import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("discountedTotal")
    val discountedTotal: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("products")
    val cartProduct: List<CartProduct>,
    @SerializedName("total")
    val total: Double,
    @SerializedName("totalProducts")
    val totalProducts: Int,
    @SerializedName("totalQuantity")
    val totalQuantity: Int,
    @SerializedName("userId")
    val userId: Int
)