package com.cevdetkilickeser.emerchant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.cevdetkilickeser.emerchant.databinding.FragmentCategoryProductsBinding
import com.cevdetkilickeser.emerchant.ui.adapter.HomeAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.CategoryProducsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryProductsFragment : Fragment() {

    private lateinit var binding: FragmentCategoryProductsBinding
    private lateinit var viewModel: CategoryProducsViewModel
    private var categoryUrl = ""
    private var categoryName = ""
    val bundle: CategoryProductsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryProductsBinding.inflate(layoutInflater, container, false)

        binding.categoryProductsPageTitle.text = categoryName

        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            val homeAdapter = HomeAdapter(requireActivity(), productList)
            binding.rvCategoryProducts.adapter = homeAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryUrl = bundle.category.slug
        categoryName = bundle.category.name

        val tempViewModel: CategoryProducsViewModel by viewModels()
        viewModel = tempViewModel
        viewModel.getProductsByCategory(categoryUrl)
    }
}