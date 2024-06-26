package com.cevdetkilickeser.emerchant.data.entity.cart

import com.cevdetkilickeser.emerchant.data.entity.product.Product

data class CartRequest(
    val userId: Int,
    val products: List<Product>
)
