package com.cevdetkilickeser.emerchant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.databinding.FragmentHomeBinding
import com.cevdetkilickeser.emerchant.ui.adapter.HomeAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            val homeAdapter = HomeAdapter(requireContext(), productList)
            binding.rvHome.adapter = homeAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: HomeViewModel by viewModels()
        viewModel = tempViewModel
    }

}