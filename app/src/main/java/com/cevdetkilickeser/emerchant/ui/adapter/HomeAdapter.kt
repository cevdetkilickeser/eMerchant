package com.cevdetkilickeser.emerchant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.databinding.ProductViewBinding

class HomeAdapter (private var context: Context, private var productList: List<Product>) : RecyclerView.Adapter<HomeAdapter.ProductViewHolder>(){


    inner class ProductViewHolder(var binding: ProductViewBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ProductViewBinding.inflate(layoutInflater)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        val b = holder.binding

        Glide.with(context).load(product.thumbnail).into(b.productViewImage)
        b.productViewTitle.text = product.title
        b.productViewPrice.text = product.price.toString()
        b.productViewRate.text = product.rating.toString()
        b.productViewCard.setOnClickListener {
            //Detay Activity geçiş kodu yazılacak
        }
    }

}