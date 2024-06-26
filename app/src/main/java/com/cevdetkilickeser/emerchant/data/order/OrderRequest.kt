package com.cevdetkilickeser.emerchant.data.order

data class OrderRequest(val userId: Int, val orderProducts: List<OrderProduct>)
