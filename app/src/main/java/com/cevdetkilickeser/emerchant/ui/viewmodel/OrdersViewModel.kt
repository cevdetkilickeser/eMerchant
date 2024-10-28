package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.model.order.Order
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _orderList = MutableLiveData<List<Order>>()
    val orderList: LiveData<List<Order>> = _orderList

    fun getCarts(userId: String) {
        viewModelScope.launch {
            val userIdInt = userId.toInt()
            _orderList.value = repository.getFireBaseOrder(userIdInt)
        }
    }
}