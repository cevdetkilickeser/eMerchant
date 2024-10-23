package com.cevdetkilickeser.emerchant.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.entity.cart.CartProduct
import com.cevdetkilickeser.emerchant.databinding.CartProductViewBinding

class CartAdapter(
    private var cartProducts: List<CartProduct>,
    private val onClickButtonIncrease: (Int, Boolean) -> Unit,
    private val onClickButtonDecrease: (Int, Boolean) -> Unit
) :
    RecyclerView.Adapter<CartProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartProductViewBinding.inflate(layoutInflater, parent, false)
        return CartProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartProducts.size
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        holder.bind(cartProducts[position], onClickButtonIncrease, onClickButtonDecrease)
    }
}