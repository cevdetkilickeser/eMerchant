package com.cevdetkilickeser.emerchant.utils

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable
import com.cevdetkilickeser.emerchant.data.model.cart.Cart
import com.cevdetkilickeser.emerchant.data.model.order.Order
import java.util.Locale

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

fun formatPrice(price: Double?): String {
    return buildString {
        append("$ ")
        append(String.format(Locale.US, "%.2f", price))
    }
}

fun mapCartToOrder(cart: Cart): Order {
    return Order(
        id = cart.id.toString(),
        orderProducts = cart.cartProducts,
        total = cart.total,
        discountedTotal = cart.discountedTotal,
        userId = cart.userId,
        totalProducts = cart.totalProducts,
        totalQuantity = cart.totalQuantity
    )
}