package com.cevdetkilickeser.emerchant.data.entity.order


import com.google.gson.annotations.SerializedName

data class OrderProduct(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("total")
    val total: Double,
    @SerializedName("discountPercentage")
    val discountPercentage: Double,
    @SerializedName("discountedTotal")
    val discountedTotal: Double,
    @SerializedName("thumbnail")
    val thumbnail: String
)