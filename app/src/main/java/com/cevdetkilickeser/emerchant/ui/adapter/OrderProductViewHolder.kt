package com.cevdetkilickeser.emerchant.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.order.OrderProduct
import com.cevdetkilickeser.emerchant.databinding.OrderProductViewBinding
import java.util.Locale

class OrderProductViewHolder(val binding: OrderProductViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(orderProduct: OrderProduct) {
        with(binding) {
            Glide.with(this.root).load(orderProduct.thumbnail).into(this.imageViewOrderProduct)
            orderProductTitle.text = orderProduct.title
            orderProductQuantity.text = orderProduct.quantity.toString()
            orderProductPrice.text = buildString {
                append("$ ")
                append(String.format(Locale.US, "%.2f", orderProduct.price))
            }
            orderProductTotal.text = buildString {
                append("$ ")
                append(String.format(Locale.US, "%.2f", orderProduct.total))
            }
        }
    }
}