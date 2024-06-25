package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val productList = MutableLiveData<List<Product>>()

    init {
        getProducts()
    }

    private fun getProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            productList.value = repository.getProducts()
        }
    }
}