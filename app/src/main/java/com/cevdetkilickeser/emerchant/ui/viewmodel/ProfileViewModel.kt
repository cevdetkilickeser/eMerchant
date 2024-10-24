package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.model.profile.Profile
import com.cevdetkilickeser.emerchant.data.model.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val profile = MutableLiveData<Profile>()
    var isProgress = MutableLiveData<Boolean>()

    fun getAuthUserProfile(token: String) {
        viewModelScope.launch {
            isProgress.value = true
            profile.value = repository.getAuthUserProfile(token)
            isProgress.value = false
        }
    }

    fun updateProfile(userId: Int, updateProfileRequest: UpdateProfileRequest) {
        viewModelScope.launch {
            isProgress.value = true
            profile.value = repository.updateProfile(userId, updateProfileRequest)
            isProgress.value = false
        }
    }
}