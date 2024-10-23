package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.entity.cart.CartProduct
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _cart = MutableLiveData<Cart?>()

    private val _cartProductList = MutableLiveData<List<CartProduct>>()
    val cartProductList: LiveData<List<CartProduct>> = _cartProductList

    private val _isProgress = MutableLiveData<Boolean>()
    val isProgress: LiveData<Boolean> = _isProgress

    private val _isEmptyCart = MutableLiveData<Boolean>()
    val isEmptyCart: LiveData<Boolean> = _isEmptyCart

    private val _cartTotal = MutableLiveData<Double>()
    val cartTotal: LiveData<Double> = _cartTotal

    fun getCart(userId: Int) {
        viewModelScope.launch {
            _cart.value = repository.getCart(userId)
            _cartProductList.value = _cart.value?.cartProducts ?: emptyList()
            _isProgress.value = false
            _isEmptyCart.value = _cart.value == null
            _cartTotal.value = _cart.value?.total ?: 0.0
        }
    }

    fun updateCartProductQuantity(userId: Int, productId: Int, updateUp: Boolean) {
        viewModelScope.launch {
            if (updateUp) {
                _isProgress.value = true
                repository.increaseQuantity(userId, productId)
            } else {
                _isProgress.value = true
                repository.decreaseQuantity(userId, productId)
            }
            _cart.value = repository.getCart(userId)
            _isProgress.value = false
        }
    }
}