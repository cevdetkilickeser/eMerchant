package com.cevdetkilickeser.emerchant.data.model.cart


import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("id")
    val id: Int,
    @SerializedName("products")
    val cartProducts: List<CartProduct>,
    @SerializedName("total")
    val total: Double,
    @SerializedName("discountedTotal")
    val discountedTotal: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("totalProducts")
    val totalProducts: Int,
    @SerializedName("totalQuantity")
    val totalQuantity: Int
)