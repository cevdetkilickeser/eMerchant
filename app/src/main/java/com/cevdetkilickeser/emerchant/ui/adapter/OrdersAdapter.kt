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
        return cartList[0].cartProduct.size
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val cartProduct = cartList[0].cartProduct[position]
        val b = holder.binding

        Glide.with(context).load(cartProduct.thumbnail).into(b.cartPrductViewImage)
        b.cartPrductViewTitle.text = cartProduct.title
        b.cartPrductViewPrice.text = "$ " + cartProduct.price.toString()
        b.cartPrductViewQuantity.text = cartProduct.quantity.toString()
        b.cartPrductViewTotal.text = "$ " + String.format("%.2f", cartProduct.total)

    }
}