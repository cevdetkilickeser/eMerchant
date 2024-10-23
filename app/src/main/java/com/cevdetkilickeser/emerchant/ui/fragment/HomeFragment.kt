package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.databinding.FragmentHomeBinding
import com.cevdetkilickeser.emerchant.ui.activity.DetailActivity
import com.cevdetkilickeser.emerchant.ui.adapter.ProductAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            val homeAdapter = ProductAdapter(productList) { product ->
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("product", product)
                requireActivity().startActivity(intent)
            }
            binding.rvHome.adapter = homeAdapter
        }

        return binding.root
    }
}