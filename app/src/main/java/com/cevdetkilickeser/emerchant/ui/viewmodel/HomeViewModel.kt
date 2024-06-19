package com.cevdetkilickeser.emerchant.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.repo.ServiceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repository: ServiceRepository) {

    val productList = MutableLiveData<List<Product>>()

    fun getProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            productList.value = repository.getProducts()
        }
    }
}