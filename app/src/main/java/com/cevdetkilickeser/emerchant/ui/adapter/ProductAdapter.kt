package com.cevdetkilickeser.emerchant.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.databinding.ProductViewBinding
import com.cevdetkilickeser.emerchant.ui.activity.DetailActivity

class ProductAdapter(private var context: Activity, private var productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(var binding: ProductViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ProductViewBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.binding.apply {
            Glide.with(this.root).load(product.thumbnail).into(this.productViewImage)
            productViewTitle.text = product.title
            productViewPrice.text = product.price.toString()
            productViewRate.text = product.rating.toString()
            productViewCard.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("product", product)
                context.startActivity(intent)
            }
        }
    }
}