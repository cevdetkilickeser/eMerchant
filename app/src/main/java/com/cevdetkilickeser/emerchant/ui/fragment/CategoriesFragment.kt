package com.cevdetkilickeser.emerchant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.databinding.FragmentCategoriesBinding
import com.cevdetkilickeser.emerchant.ui.adapter.CategoriesAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.CategoriesViewModel
import com.cevdetkilickeser.emerchant.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var viewModel: CategoriesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)

        viewModel.caregoryList.observe(viewLifecycleOwner) { caregoryList ->
            val homeAdapter = CategoriesAdapter(requireContext(), caregoryList)
            binding.rvCategories.adapter = homeAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: CategoriesViewModel by viewModels()
        viewModel = tempViewModel
    }
}