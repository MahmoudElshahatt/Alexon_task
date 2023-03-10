package com.shahtott.alexon_task.ui.main.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahtott.alexon_task.ui.main.products.models.ProductsResponse
import com.shahtott.alexon_task.ui.main.products.models.ProductsResponse.Product
import com.shahtott.alexon_task.util.network.ErrorResponse
import com.shahtott.alexon_task.util.network.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(val productsRepository: ProductsRepository) :
    ViewModel() {

    /**
     * UI states
     */

    private val _products = MutableLiveData<List<Product>?>()
    val products: LiveData<List<Product>?> = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _generalError = MutableLiveData<String>()
    val generalError: LiveData<String> = _generalError

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean> = _networkError

    private var _job: Job? = null

    init {
        getProducts()
    }

    fun getProducts() {
        _job = viewModelScope.launch {
            _isLoading.value = true
            val productResult = productsRepository.getProducts()
            when (productResult) {
                is ResultWrapper.GenericError -> showGeneralError(productResult.error)
                is ResultWrapper.NetworkError -> showNetworkError()
                is ResultWrapper.Success<ProductsResponse> -> showSuccess(productResult.value)
            }
            _isLoading.postValue(false)
        }
    }

    private fun showNetworkError() {
        _networkError.value = true
    }

    private fun showGeneralError(error: ErrorResponse?) {
        _generalError.value = error?.message ?: ""
    }

    private fun showSuccess(productsResponse: ProductsResponse) {
        _products.value = productsResponse.products
    }

    fun cancelCurrentJob() {
        if (_job?.isActive == true) {
            _job?.cancel()
        }
    }

    fun resetErrorStates() {
        _generalError.value = ""
        _networkError.value = false
    }

}