package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val cartList = MutableLiveData<List<Cart>>()
    val total = MutableLiveData<Double>()

    fun getCarts(userId: String) {
        viewModelScope.launch {
            cartList.value = repository.getCarts(userId)
            if (cartList.value.isNullOrEmpty()) {
                total.value = 0.0
            } else {
                total.value = cartList.value!!.first().total
            }
        }
    }
}