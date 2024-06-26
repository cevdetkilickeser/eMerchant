package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.entity.like.UnlikeClickEvent
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

@HiltViewModel
class LikesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val likeList = MutableLiveData<List<Like>>()

    init {
        EventBus.getDefault().register(this)
    }

    fun getLikes(userId: String) {
        viewModelScope.launch {
            likeList.value = repository.getLikes(userId)
        }
    }

    private fun deleteLike(like: Like) {
        viewModelScope.launch {
            repository.deleteLike(like)
            getLikes(like.userId)
        }
    }

    @Subscribe
    fun onUnlikeClickEvent(event: UnlikeClickEvent) {
        deleteLike(event.like)
    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }
}