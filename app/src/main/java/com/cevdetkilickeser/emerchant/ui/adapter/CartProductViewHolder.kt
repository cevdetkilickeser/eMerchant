package com.cevdetkilickeser.emerchant.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.cart.CartProduct
import com.cevdetkilickeser.emerchant.databinding.CartProductViewBinding
import java.util.Locale

class CartProductViewHolder(var binding: CartProductViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        cartProduct: CartProduct,
        onClickButtonIncrease: (Int, Boolean) -> Unit,
        onClickButtonDecrease: (Int, Boolean) -> Unit
    ) {
        binding.apply {
            Glide.with(this.root).load(cartProduct.thumbnail).into(this.imageViewCartProductView)
            cartProductTitle.text = cartProduct.title
            cartProductPrice.text = buildString {
                append("$ ")
                append(String.format(Locale.US, "%.2f", cartProduct.price))
            }
            cartProductQuantity.text = cartProduct.quantity.toString()
            cartProductTotal.text = buildString {
                append("$ ")
                append(String.format(Locale.US, "%.2f", cartProduct.total))
            }
            buttonIncrease.setOnClickListener {
                onClickButtonIncrease.invoke(cartProduct.id, true)
            }
            buttonDecrease.setOnClickListener {
                onClickButtonDecrease.invoke(cartProduct.id, false)
            }
        }
    }
}