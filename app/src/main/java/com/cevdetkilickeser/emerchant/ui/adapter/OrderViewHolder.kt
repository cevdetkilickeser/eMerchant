package com.cevdetkilickeser.emerchant.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.entity.order.Order
import com.cevdetkilickeser.emerchant.databinding.OrderViewBinding
import com.cevdetkilickeser.emerchant.utils.formatPrice

class OrderViewHolder(val binding: OrderViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(order: Order) {
        with(binding) {
            orderNumber.text = order.id.toString()
            orderTotal.text = formatPrice(order.total)
            val orderProductAdapter = OrderProductAdapter(order.orderProducts)
            rvOrderProduct.adapter = orderProductAdapter
        }
    }
}