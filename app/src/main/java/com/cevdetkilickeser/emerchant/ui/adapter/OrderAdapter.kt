package com.cevdetkilickeser.emerchant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.entity.order.Order
import com.cevdetkilickeser.emerchant.databinding.OrderViewBinding
import java.util.Locale

class OrderAdapter(private val context: Context, private var orderList: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private lateinit var orderProductAdapter: OrderProductAdapter

    inner class OrderViewHolder(var binding: OrderViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = OrderViewBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]

        holder.binding.apply {
            orderNumber.text = order.id.toString()
            orderTotal.text = buildString {
                append("$ ")
                append(String.format(Locale.US, "%.2f", order.total))
            }
            orderProductAdapter = OrderProductAdapter(context, order.orderProducts)
            rvOrderProduct.adapter = orderProductAdapter
        }
    }
}