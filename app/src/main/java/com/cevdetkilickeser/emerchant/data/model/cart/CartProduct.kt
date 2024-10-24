package com.cevdetkilickeser.emerchant.data.model.cart


import com.google.gson.annotations.SerializedName

data class CartProduct(
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
    @SerializedName("discountedPrice")
    val discountedPrice: Int,
    @SerializedName("thumbnail")
    val thumbnail: String
)