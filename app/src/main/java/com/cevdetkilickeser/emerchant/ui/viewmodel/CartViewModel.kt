package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.model.cart.Cart
import com.cevdetkilickeser.emerchant.data.model.cart.CartProduct
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _cart = MutableLiveData<Cart?>()
    val cart: LiveData<Cart?> = _cart

    private val _cartProductList = MutableLiveData<List<CartProduct>>()
    val cartProductList: LiveData<List<CartProduct>> = _cartProductList

    private val _isProgress = MutableLiveData<Boolean>()
    val isProgress: LiveData<Boolean> = _isProgress

    private val _isEmptyCart = MutableLiveData<Boolean>()
    val isEmptyCart: LiveData<Boolean> = _isEmptyCart

    fun getCart(userId: Int) {
        viewModelScope.launch {
            _isProgress.value = true
            _cart.value = repository.getCart(userId)
            _cartProductList.value = _cart.value?.cartProducts ?: emptyList()
            _isProgress.value = false
            _isEmptyCart.value = _cart.value == null
        }
    }

    fun updateCart(userId: Int, cartProduct: CartProduct, isUpdateUp: Boolean) {
        viewModelScope.launch {
            _isProgress.value = true
            if (isUpdateUp) {
                repository.increaseQuantity(userId, cartProduct.id) {}
            } else {
                repository.decreaseQuantity(userId, cartProduct.id)
            }
            getCart(userId)
        }
    }

    fun checkout(userId: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.checkout(userId, _cart.value!!, onResult)
        }
    }
}