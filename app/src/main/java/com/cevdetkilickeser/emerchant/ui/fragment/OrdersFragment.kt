package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.data.entity.order.Order
import com.cevdetkilickeser.emerchant.databinding.FragmentOrdersBinding
import com.cevdetkilickeser.emerchant.ui.adapter.OrderAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    private val viewModel: OrdersViewModel by viewModels()
    private lateinit var userId: String
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)

        viewModel.orderList.observe(viewLifecycleOwner) { orderList ->
            showNoOrder(orderList)
            val orderAdapter = OrderAdapter(orderList)
            binding.rvOrders.adapter = orderAdapter
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        userId = sharedPref.getString("userId", "").toString()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCarts(userId)
    }

    private fun showNoOrder(orderList: List<Order>) {
        if (orderList.isEmpty()) {
            binding.noOrderText.visibility = View.VISIBLE
        } else {
            binding.noOrderText.visibility = View.GONE
        }
    }
}