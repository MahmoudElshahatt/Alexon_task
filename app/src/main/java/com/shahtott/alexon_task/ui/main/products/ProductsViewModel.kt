package com.shahtott.alexon_task.ui.main.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor( val productsRepository: ProductsRepository) :
    ViewModel() {

    /**
     * UI states
     */


    fun getProducts() {
        viewModelScope.launch {
            val result = productsRepository.getProducts()

        }
    }
}