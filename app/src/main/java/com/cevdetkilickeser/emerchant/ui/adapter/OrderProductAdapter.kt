package com.cevdetkilickeser.emerchant.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.entity.order.OrderProduct
import com.cevdetkilickeser.emerchant.databinding.OrderProductViewBinding

class OrderProductAdapter(
    private var orderProductList: List<OrderProduct>
) : RecyclerView.Adapter<OrderProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OrderProductViewBinding.inflate(layoutInflater, parent, false)
        return OrderProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderProductList.size
    }

    override fun onBindViewHolder(holder: OrderProductViewHolder, position: Int) {
        holder.bind(orderProductList[position])
    }
}