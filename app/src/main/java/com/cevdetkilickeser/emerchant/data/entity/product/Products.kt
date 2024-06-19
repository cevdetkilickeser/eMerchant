package com.cevdetkilickeser.emerchant.data.entity.product


import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)