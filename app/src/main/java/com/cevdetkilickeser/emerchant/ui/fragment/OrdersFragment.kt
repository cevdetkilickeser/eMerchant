package com.cevdetkilickeser.emerchant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.databinding.FragmentOrdersBinding
import com.cevdetkilickeser.emerchant.ui.adapter.OrdersAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    private lateinit var viewModel: OrdersViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(layoutInflater)

        viewModel.cartList.observe(viewLifecycleOwner) { cartList ->
            val ordersAdapter = OrdersAdapter(requireContext(), cartList)
            binding.rvOrders.adapter = ordersAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: OrdersViewModel by viewModels()
        viewModel = tempViewModel
    }
}