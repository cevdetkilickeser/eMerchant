package com.cevdetkilickeser.emerchant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.databinding.CartProductViewBinding

class OrdersAdapter(private var context: Context, private var cartList: List<Cart>) :
    RecyclerView.Adapter<OrdersAdapter.CartProductViewHolder>() {

    inner class CartProductViewHolder(var binding: CartProductViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = CartProductViewBinding.inflate(layoutInflater)
        return CartProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return try {
            cartList[0].cartProduct.size
        } catch (_: Exception) {
            0
        }

    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        if (cartList.isNotEmpty()) {
            val cart = cartList[0]
            val cartProduct = cart.cartProduct[position]

            holder.binding.apply {
                Glide.with(this.root).load(cartProduct.thumbnail).into(this.cartPrductViewImage)
                cartPrductViewTitle.text = cartProduct.title
                cartPrductViewPrice.text = "$ " + cartProduct.price.toString()
                cartPrductViewQuantity.text = cartProduct.quantity.toString()
                cartPrductViewTotal.text = "$ " + String.format("%.2f", cartProduct.total)
            }
        }
    }
}