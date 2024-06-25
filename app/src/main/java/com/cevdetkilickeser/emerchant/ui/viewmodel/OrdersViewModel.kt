package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val cartList = MutableLiveData<List<Cart>>()
    private val userId = "6"

    init {
        getCarts(userId)
    }

    private fun getCarts(userId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            cartList.value = repository.getCarts(userId)
        }
    }
}