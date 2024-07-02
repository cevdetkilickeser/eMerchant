package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.entity.order.Order
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val orderList = MutableLiveData<List<Order>>()
//    val total = MutableLiveData<Double>()

    fun getCarts(userId: String) {
        viewModelScope.launch {
            orderList.value = repository.getOrders(userId)
//            if (orderList.value.isNullOrEmpty()) {
//                total.value = 0.0
//            } else {
//                total.value = orderList.value!!.first().total
//            }
        }
    }
}