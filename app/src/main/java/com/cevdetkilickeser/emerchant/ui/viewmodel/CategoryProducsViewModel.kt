package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.emerchant.data.model.product.Product
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryProducsViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    val productList = MutableLiveData<List<Product>>()

    fun getProductsByCategory(category: String) {
        viewModelScope.launch {
            productList.value = repository.getProductsByCategory(category)
        }
    }

}