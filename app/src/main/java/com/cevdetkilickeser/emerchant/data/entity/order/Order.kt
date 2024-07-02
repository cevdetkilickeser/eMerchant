package com.cevdetkilickeser.emerchant.data.entity.order


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("id")
    val id: Int,
    @SerializedName("products")
    val orderProducts: List<OrderProduct>,
    @SerializedName("total")
    val total: Double,
    @SerializedName("discountedTotal")
    val discountedTotal: Double,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("totalProducts")
    val totalProducts: Int,
    @SerializedName("totalQuantity")
    val totalQuantity: Int
)