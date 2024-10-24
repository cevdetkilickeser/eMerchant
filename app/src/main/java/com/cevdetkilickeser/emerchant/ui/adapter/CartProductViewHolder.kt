package com.cevdetkilickeser.emerchant.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.model.cart.CartProduct
import com.cevdetkilickeser.emerchant.databinding.CartProductViewBinding
import com.cevdetkilickeser.emerchant.utils.formatPrice

class CartProductViewHolder(var binding: CartProductViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        cartProduct: CartProduct,
        updateCart: (CartProduct, Boolean) -> Unit
    ) {
        with(binding) {
            Glide.with(this.root).load(cartProduct.thumbnail).into(this.imageViewCartProductView)
            cartProductTitle.text = cartProduct.title
            cartProductPrice.text = formatPrice(cartProduct.price)
            cartProductQuantity.text = cartProduct.quantity.toString()
            cartProductTotal.text = formatPrice(cartProduct.total)
            buttonIncrease.setOnClickListener {
                updateCart.invoke(cartProduct, true)
            }

            buttonDecrease.setOnClickListener {
                updateCart.invoke(cartProduct, false)
            }
        }
    }
}