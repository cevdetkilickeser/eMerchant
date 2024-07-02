package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.databinding.FragmentOrdersBinding
import com.cevdetkilickeser.emerchant.ui.adapter.OrderAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    private lateinit var viewModel: OrdersViewModel
    private lateinit var userId: String
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)

        viewModel.orderList.observe(viewLifecycleOwner) { orderList ->
            if (orderList.isEmpty()) {
                binding.noOrderText.visibility = View.VISIBLE
            } else {
                binding.noOrderText.visibility = View.GONE
            }
            val orderAdapter = OrderAdapter(requireContext(), orderList)
            binding.rvOrders.adapter = orderAdapter
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: OrdersViewModel by viewModels()
        viewModel = tempViewModel

        sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        userId = sharedPref.getString("userId", "").toString()
        viewModel.getCarts(userId)
    }
}