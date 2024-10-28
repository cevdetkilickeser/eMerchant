package com.cevdetkilickeser.emerchant.data.model.order

import com.google.firebase.Timestamp

data class FirebaseOrderResponse(
    val userId: Int? = null,
    val order: Order? = null,
    val orderDate: Timestamp? = null
)
