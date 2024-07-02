package com.cevdetkilickeser.emerchant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.databinding.CategoryViewBinding
import com.cevdetkilickeser.emerchant.ui.fragment.CategoriesFragmentDirections

class CategoryAdapter(private val context: Context, private var categoryList: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoriesViewHolder>() {


    inner class CategoriesViewHolder(var binding: CategoryViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = CategoryViewBinding.inflate(layoutInflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categoryList[position]

        holder.binding.apply {
            categoryViewName.text = category.name
            categoryViewCard.setOnClickListener {
                val action = CategoriesFragmentDirections.categoriesToCategoryProducts(category)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}