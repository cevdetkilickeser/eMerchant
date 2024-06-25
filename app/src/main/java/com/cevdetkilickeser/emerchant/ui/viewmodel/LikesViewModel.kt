package com.cevdetkilickeser.emerchant.ui.viewmodel

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

    val likeList = MutableLiveData<List<Like>>()

    fun getLikes(userId: String) {
        viewModelScope.launch {
            likeList.value = repository.getLikes(userId)
        }
    }
}