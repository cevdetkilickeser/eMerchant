package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.repo.ServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: ServiceRepository) : ViewModel() {

    val caregoryList = MutableLiveData<List<Category>>()

    init {
        getCategories()
    }

    private fun getCategories() {
        CoroutineScope(Dispatchers.Main).launch {
            caregoryList.value = repository.getCategories()
        }
    }
}