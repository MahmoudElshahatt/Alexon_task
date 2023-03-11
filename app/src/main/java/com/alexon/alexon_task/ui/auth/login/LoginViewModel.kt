package com.alexon.alexon_task.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexon.alexon_task.util.network.ErrorResponse
import com.alexon.alexon_task.util.network.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _generalError = MutableLiveData<String>()
    val generalError: LiveData<String> = _generalError

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean> = _networkError

    private var _job: Job? = null

    fun loginClicked(email: String, password: String) {
        _isLoading.value = true
        // after that login user
        _job = viewModelScope.launch {
            val loginRes = loginRepository.login(email, password)
            when (loginRes) {
                is ResultWrapper.NetworkError -> showNetworkError()
                is ResultWrapper.GenericError -> showGeneralError(loginRes.error)
                is ResultWrapper.Success<*> -> showSuccess()
            }
            _isLoading.postValue(false)

        }
    }
    fun cancelCurrentJob() {
        if (_job?.isActive == true) {
            _job?.cancel()
        }
    }

    private fun showNetworkError() {
        _networkError.value = true
    }

    private fun showGeneralError(error: ErrorResponse?) {
        _generalError.value = error?.message ?: ""
    }

    private fun showSuccess() {
        _isSuccess.value = true
    }

    fun resetErrorStates() {
        _generalError.value = ""
        _networkError.value = false
    }
}
