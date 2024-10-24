package com.cevdetkilickeser.emerchant.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.model.category.Category
import com.cevdetkilickeser.emerchant.databinding.CategoryViewBinding

class CategoryAdapter(
    private var categoryList: List<Category>,
    private val onClickCategory: (Category) -> Unit
) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoryViewBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position], onClickCategory)
    }
}