package com.cevdetkilickeser.emerchant.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.databinding.ProductViewBinding

class ProductViewHolder(var binding: ProductViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product, onClickProduct: (Product) -> Unit) {
        with(binding) {
            Glide.with(this.root).load(product.thumbnail).into(this.productViewImage)
            productViewTitle.text = product.title
            productViewPrice.text = product.price.toString()
            productViewRate.text = product.rating.toString()
            productViewCard.setOnClickListener {
                onClickProduct.invoke(product)
            }
        }
    }
}