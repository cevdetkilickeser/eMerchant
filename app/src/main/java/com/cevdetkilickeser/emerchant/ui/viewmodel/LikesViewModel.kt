package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _likeList = MutableLiveData<List<Like>>()
    val likeList: LiveData<List<Like>> = _likeList

    fun getLikes(userId: String) {
        viewModelScope.launch {
            _likeList.value = repository.getLikes(userId)
        }
    }

    fun deleteLike(like: Like) {
        viewModelScope.launch {
            repository.deleteLike(like)
            getLikes(like.userId)
        }
    }
}