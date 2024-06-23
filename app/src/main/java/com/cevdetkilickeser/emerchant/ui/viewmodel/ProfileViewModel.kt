package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.repo.ServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ServiceRepository) :
    ViewModel() {

    val profile = MutableLiveData<Profile>()
    val userId = "1"

    private fun getProfile(userId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            profile.value = repository.getProfile(userId)
        }
    }

    fun getAuthUserProfile(token: String) {
        CoroutineScope(Dispatchers.Main).launch {
            profile.value = repository.getAuthUserProfile(token)
        }
    }
}