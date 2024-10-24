package com.cevdetkilickeser.emerchant.data.model.cart

import com.google.gson.annotations.SerializedName

data class CartRequest(
    @SerializedName("userId") val userId: Int,
    @SerializedName("products") val cartRequestProducts: List<CartRequestProduct>
)