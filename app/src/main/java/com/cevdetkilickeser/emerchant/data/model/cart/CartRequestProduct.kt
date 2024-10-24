package com.cevdetkilickeser.emerchant.data.model.cart

import com.google.gson.annotations.SerializedName

data class CartRequestProduct(
    @SerializedName("id")
    val productId: Int,
    @SerializedName("quantity")
    val quantity: Int
)
