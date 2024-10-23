package com.cevdetkilickeser.emerchant.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.databinding.CategoryViewBinding

class CategoryViewHolder(var binding: CategoryViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category, onClickCategory: (Category) -> Unit) {
        binding.apply {
            categoryViewName.text = category.name
            categoryViewCard.setOnClickListener {
                onClickCategory.invoke(category)
            }
        }
    }

}