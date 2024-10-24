package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.model.category.Category
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> = _categoryList

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            _categoryList.value = repository.getCategories()
        }
    }
}