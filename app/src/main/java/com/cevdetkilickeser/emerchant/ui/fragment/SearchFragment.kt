package com.cevdetkilickeser.emerchant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.databinding.FragmentSearchBinding
import com.cevdetkilickeser.emerchant.ui.adapter.HomeAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            val homeAdapter = HomeAdapter(requireContext(), productList)
            binding.rvSearch.adapter = homeAdapter
        }

        binding.searchbox.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    viewModel.productList.value = emptyList()
                } else {
                    viewModel.searchProducts(newText)
                }
                return false
            }
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: SearchViewModel by viewModels()
        viewModel = tempViewModel
    }
}