package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.data.entity.cart.CartProduct
import com.cevdetkilickeser.emerchant.databinding.FragmentCartBinding
import com.cevdetkilickeser.emerchant.ui.adapter.CartAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var sharedPref: SharedPreferences
    private var userId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)

        viewModel.cart.observe(viewLifecycleOwner) { cart ->
            var cartProducts = listOf<CartProduct>()
            cart?.let {
                cartProducts = it.cartProducts
                binding.cartProductsTotal.text = buildString {
                    append("$ ")
                    append(String.format(Locale.US, "%.2f", it.total))
                }
                binding.imageViewEmptyCart.visibility = View.GONE
                binding.cardViewCartTotal.visibility = View.VISIBLE
            } ?: run {
                cartProducts = emptyList()
                binding.imageViewEmptyCart.visibility = View.VISIBLE
                binding.cardViewCartTotal.visibility = View.GONE
            }
            val cartAdapter = CartAdapter(requireContext(), userId, cartProducts)
            binding.rvCart.adapter = cartAdapter
        }

        viewModel.isProgress.observe(viewLifecycleOwner) { isProgress ->
            if (isProgress) {
                binding.progressBarCart.visibility = View.VISIBLE
            } else {
                binding.progressBarCart.visibility = View.GONE
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: CartViewModel by viewModels()
        viewModel = tempViewModel

        sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        userId = sharedPref.getString("userId", "").toString().toInt()
        viewModel.getCart(userId)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCart(userId)
    }
}