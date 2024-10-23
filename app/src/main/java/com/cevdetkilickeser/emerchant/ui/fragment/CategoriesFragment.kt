package com.cevdetkilickeser.emerchant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.cevdetkilickeser.emerchant.databinding.FragmentCategoriesBinding
import com.cevdetkilickeser.emerchant.ui.adapter.CategoryAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val viewModel: CategoriesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)

        viewModel.categoryList.observe(viewLifecycleOwner) { categoryList ->
            val categoryAdapter = CategoryAdapter(categoryList) { category ->
                val action = CategoriesFragmentDirections.categoriesToCategoryProducts(category)
                Navigation.findNavController(binding.root).navigate(action)
            }
            binding.rvCategories.adapter = categoryAdapter
        }

        return binding.root
    }
}