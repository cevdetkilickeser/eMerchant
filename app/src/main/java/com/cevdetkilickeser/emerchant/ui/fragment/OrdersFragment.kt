package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Context
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
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)

        viewModel.cartList.observe(viewLifecycleOwner) { cartList ->
            val ordersAdapter = OrdersAdapter(requireContext(), cartList)
            binding.rvOrders.adapter = ordersAdapter
        }

        viewModel.total.observe(viewLifecycleOwner) { total ->
            if (total > 0) {
                binding.orderPageTotal.text = "$ " + total
                binding.cardViewTotal.visibility = View.VISIBLE
                binding.imageViewEmptyCart.visibility = View.GONE
            } else {
                binding.imageViewEmptyCart.visibility = View.VISIBLE
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: OrdersViewModel by viewModels()
        viewModel = tempViewModel

        val sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        userId = sharedPref.getString("userId", "").toString()
        viewModel.getCarts(userId)
    }
}