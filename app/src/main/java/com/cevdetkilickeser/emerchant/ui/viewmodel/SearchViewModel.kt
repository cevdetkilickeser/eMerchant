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
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val productList = MutableLiveData<List<Product>>()

    fun searchProducts(query: String) {
        viewModelScope.launch {
            productList.value = repository.searchProducts(query)
        }
    }
}