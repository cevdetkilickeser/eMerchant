package com.cevdetkilickeser.emerchant.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.order.OrderProduct
import com.cevdetkilickeser.emerchant.databinding.OrderProductViewBinding
import com.cevdetkilickeser.emerchant.utils.formatPrice

class OrderProductViewHolder(val binding: OrderProductViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(orderProduct: OrderProduct) {
        with(binding) {
            Glide.with(this.root).load(orderProduct.thumbnail).into(this.imageViewOrderProduct)
            orderProductTitle.text = orderProduct.title
            orderProductQuantity.text = orderProduct.quantity.toString()
            orderProductPrice.text = formatPrice(orderProduct.price)
            orderProductTotal.text = formatPrice(orderProduct.total)
        }
    }
}