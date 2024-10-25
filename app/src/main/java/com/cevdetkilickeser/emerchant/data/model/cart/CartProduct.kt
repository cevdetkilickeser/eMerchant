package com.cevdetkilickeser.emerchant.data.model.cart


import com.google.gson.annotations.SerializedName

data class CartProduct(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("price")
    val price: Double = 0.0,
    @SerializedName("quantity")
    val quantity: Int = 0,
    @SerializedName("total")
    val total: Double = 0.0,
    @SerializedName("discountPercentage")
    val discountPercentage: Double = 0.0,
    @SerializedName("discountedPrice")
    val discountedPrice: Int = 0,
    @SerializedName("thumbnail")
    val thumbnail: String = ""
)