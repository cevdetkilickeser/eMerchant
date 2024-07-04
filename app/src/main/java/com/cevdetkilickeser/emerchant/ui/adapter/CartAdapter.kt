package com.cevdetkilickeser.emerchant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.cart.CartProduct
import com.cevdetkilickeser.emerchant.data.entity.cart.UpdateQuantityClickEvent
import com.cevdetkilickeser.emerchant.databinding.CartProductViewBinding
import org.greenrobot.eventbus.EventBus
import java.util.Locale

class CartAdapter(
    private val context: Context,
    private val userId: Int,
    private var cartProducts: List<CartProduct>
) :
    RecyclerView.Adapter<CartAdapter.CartProductViewHoleder>() {
    inner class CartProductViewHoleder(var binding: CartProductViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHoleder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = CartProductViewBinding.inflate(layoutInflater, parent, false)
        return CartProductViewHoleder(binding)
    }

    override fun getItemCount(): Int {
        return cartProducts.size
    }

    override fun onBindViewHolder(holder: CartProductViewHoleder, position: Int) {
        val cartProduct = cartProducts[position]

        holder.binding.apply {
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
                EventBus.getDefault().post(UpdateQuantityClickEvent(userId, cartProduct.id, true))
            }
            buttonDecrease.setOnClickListener {
                EventBus.getDefault().post(UpdateQuantityClickEvent(userId, cartProduct.id, false))
            }
        }
    }
}