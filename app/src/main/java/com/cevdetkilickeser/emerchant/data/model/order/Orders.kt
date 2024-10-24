package com.cevdetkilickeser.emerchant.data.model.order


import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("carts")
    val orders: List<Order>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int
)