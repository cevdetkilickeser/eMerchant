package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val profile = MutableLiveData<Profile>()
    val updateProfileId = MutableLiveData<Int>()

    fun getAuthUserProfile(token: String) {
        viewModelScope.launch {
            profile.value = repository.getAuthUserProfile(token)
        }
    }

    fun updateProfile(userId: String, lastName: String) {
        viewModelScope.launch {
            updateProfileId.value = repository.updateProfile(userId, lastName)
        }
    }
}