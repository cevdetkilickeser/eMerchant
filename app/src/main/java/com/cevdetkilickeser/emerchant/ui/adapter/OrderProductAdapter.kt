package com.cevdetkilickeser.emerchant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.order.OrderProduct
import com.cevdetkilickeser.emerchant.databinding.OrderProductViewBinding
import java.util.Locale

class OrderProductAdapter(
    private val context: Context,
    private var orderProductList: List<OrderProduct>
) :
    RecyclerView.Adapter<OrderProductAdapter.OrderProductViewHolder>() {

    inner class OrderProductViewHolder(var binding: OrderProductViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderProductViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = OrderProductViewBinding.inflate(layoutInflater, parent, false)
        return OrderProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderProductList.size
    }

    override fun onBindViewHolder(holder: OrderProductViewHolder, position: Int) {
        val orderProduct = orderProductList[position]

        holder.binding.apply {
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