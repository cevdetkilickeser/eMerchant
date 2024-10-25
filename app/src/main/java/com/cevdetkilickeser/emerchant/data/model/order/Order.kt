package com.cevdetkilickeser.emerchant.data.model.order


import com.cevdetkilickeser.emerchant.data.model.cart.CartProduct
import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("products")
    val orderProducts: List<CartProduct> = emptyList(),
    @SerializedName("total")
    val total: Double = 0.0,
    @SerializedName("discountedTotal")
    val discountedTotal: Double = 0.0,
    @SerializedName("userId")
    val userId: Int = 0,
    @SerializedName("totalProducts")
    val totalProducts: Int = 0,
    @SerializedName("totalQuantity")
    val totalQuantity: Int = 0
)