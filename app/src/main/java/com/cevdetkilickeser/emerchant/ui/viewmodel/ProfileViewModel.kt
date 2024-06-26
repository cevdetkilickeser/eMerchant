package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val profile = MutableLiveData<Profile>()
    val updatedProfile = MutableLiveData<Profile>()

    fun getAuthUserProfile(token: String) {
        viewModelScope.launch {
            profile.value = repository.getAuthUserProfile(token)
        }
    }

    fun updateProfile(userId: String, updateProfileRequest: UpdateProfileRequest) {
        viewModelScope.launch {
            updatedProfile.value = repository.updateProfile(userId, updateProfileRequest)
        }
    }
}