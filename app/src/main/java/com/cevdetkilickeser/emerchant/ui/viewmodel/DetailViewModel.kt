package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.model.like.Like
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val like = MutableLiveData<Like>()

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

    fun onClickAddToCart(userId: Int, productId: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.onClickAddToCart(userId, productId, onResult)
        }
    }
}