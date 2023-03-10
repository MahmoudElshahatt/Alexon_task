package com.shahtott.alexon_task.ui.main.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahtott.alexon_task.ui.main.products.models.ProductsResponse
import com.shahtott.alexon_task.util.network.ErrorResponse
import com.shahtott.alexon_task.util.network.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.internal.aggregatedroot.codegen._com_shahtott_alexon_task_App
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(val productsRepository: ProductsRepository) :
    ViewModel() {

    /**
     * UI states
     */

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _generalError = MutableLiveData<String>()
    val generalError: LiveData<String> = _generalError

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean> = _networkError

    private var _job: Job? = null


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
        _isSuccess.value = true
    }

    fun cancelCurrentJob() {
        if (_job?.isActive == true) {
            _job?.cancel()
        }
    }
}