package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val like = MutableLiveData<Like>()
    val addCartId = MutableLiveData<Int>()

    fun checkLike(userId: String, productId: Int) {
        viewModelScope.launch {
            like.value = repository.checkLike(userId, productId)
        }
    }

    fun insertLike(like: Like) {
        viewModelScope.launch {
            repository.insertLike(like)
            checkLike(like.userId, like.productId)
        }
    }

    fun deleteLike(like: Like) {
        viewModelScope.launch {
            repository.deleteLike(like)
            checkLike(like.userId, like.productId)
        }
    }

    fun addCart(userId: String, product: List<Product>) {
        viewModelScope.launch {
            addCartId.value = repository.addCart(userId, product)
        }
    }
}