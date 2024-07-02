package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.entity.cart.UpdateQuantityClickEvent
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val cart = MutableLiveData<Cart?>()
    val isProgress = MutableLiveData<Boolean>()

    init {
        EventBus.getDefault().register(this)
    }

    fun getCart(userId: Int) {
        viewModelScope.launch {
            cart.value = repository.getCart(userId)
            isProgress.value = false
        }
    }

    private fun updateCartProductQuantity(userId: Int, productId: Int, updateUp: Boolean) {
        viewModelScope.launch {
            if (updateUp) {
                isProgress.value = true
                repository.increaseQuantity(userId, productId)
            } else {
                isProgress.value = true
                repository.decreaseQuantity(userId, productId)
            }
            cart.value = repository.getCart(userId)
            isProgress.value = false
        }
    }

    @Subscribe
    fun onUnlikeClickEvent(event: UpdateQuantityClickEvent) {
        updateCartProductQuantity(event.userId, event.productId, event.updateUp)
    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }
}