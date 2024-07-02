package com.cevdetkilickeser.emerchant.data.entity.cart

data class UpdateQuantityClickEvent(val userId: Int, val productId: Int, val updateUp: Boolean)