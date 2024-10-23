package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.databinding.FragmentCartBinding
import com.cevdetkilickeser.emerchant.ui.adapter.CartProductAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()
    private lateinit var sharedPref: SharedPreferences
    private var userId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)

        viewModel.cartProductList.observe(viewLifecycleOwner) { cartProductList ->
            binding.cartProductsTotal.text = buildString {
                append("$ ")
                append(String.format(Locale.US, "%.2f", viewModel.cartTotal))
            }

            val cartProductAdapter =
                CartProductAdapter(
                    cartProductList,
                    ::onClickButtonIncrease,
                    ::onClickButtonDecrease
                )
            binding.rvCart.adapter = cartProductAdapter
        }

        viewModel.isEmptyCart.observe(viewLifecycleOwner) { isEmptyCart ->
            showEmptyCart(isEmptyCart)
        }

        viewModel.isProgress.observe(viewLifecycleOwner) { isProgress ->
            showProgress(isProgress)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        userId = sharedPref.getString("userId", "").toString().toInt()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCart(userId)
    }

    private fun onClickButtonIncrease(cartProductId: Int, updateUp: Boolean) {
        viewModel.updateCartProductQuantity(userId, cartProductId, updateUp)
    }

    private fun onClickButtonDecrease(cartProductId: Int, updateUp: Boolean) {
        viewModel.updateCartProductQuantity(userId, cartProductId, updateUp)
    }

    private fun showEmptyCart(isEmptyCart: Boolean) {
        if (isEmptyCart) {
            binding.imageViewEmptyCart.visibility = View.VISIBLE
            binding.cardViewCartTotal.visibility = View.GONE
        } else {
            binding.imageViewEmptyCart.visibility = View.GONE
            binding.cardViewCartTotal.visibility = View.VISIBLE
        }
    }

    private fun showProgress(isProgress: Boolean) {
        if (isProgress) {
            binding.progressBarCart.visibility = View.VISIBLE
        } else {
            binding.progressBarCart.visibility = View.GONE
        }
    }
}