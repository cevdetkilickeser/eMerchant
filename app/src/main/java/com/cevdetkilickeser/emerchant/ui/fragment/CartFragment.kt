package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.data.model.cart.CartProduct
import com.cevdetkilickeser.emerchant.databinding.FragmentCartBinding
import com.cevdetkilickeser.emerchant.ui.adapter.CartProductAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.CartViewModel
import com.cevdetkilickeser.emerchant.utils.formatPrice
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

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

        binding.buttonCheckout.setOnClickListener {
            viewModel.checkout(userId, ::showSnackBar)
        }

        viewModel.cartProductList.observe(viewLifecycleOwner) { cartProductList ->
            val cartProductAdapter = CartProductAdapter(cartProductList, ::updateCart)
            binding.rvCart.adapter = cartProductAdapter
        }

        viewModel.isProgress.observe(viewLifecycleOwner) { isProgress ->
            showProgressBar(isProgress)
        }

        viewModel.isEmptyCart.observe(viewLifecycleOwner) { isEmpty ->
            showEmptyCartImage(isEmpty)
        }

        viewModel.cart.observe(viewLifecycleOwner) { cart ->
            binding.cartProductsTotal.text = formatPrice(cart?.total)
        }

        return binding.root
    }

    private fun showSnackBar(showSnackBar: Boolean) {
        if (showSnackBar) {
            Snackbar.make(binding.root, "Success", Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showProgressBar(showProgress: Boolean) {
        if (showProgress) {
            binding.progressBarCart.visibility = View.VISIBLE
        } else {
            binding.progressBarCart.visibility = View.GONE
        }
    }

    private fun showEmptyCartImage(isEmpty: Boolean) {
        if (isEmpty) {
            binding.imageViewEmptyCart.visibility = View.VISIBLE
            binding.cardViewCartTotal.visibility = View.GONE
        } else {
            binding.imageViewEmptyCart.visibility = View.GONE
            binding.cardViewCartTotal.visibility = View.VISIBLE
        }
    }

    private fun updateCart(cartProduct: CartProduct, isUpdateUp: Boolean) {
        viewModel.updateCart(userId, cartProduct, isUpdateUp)
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
}