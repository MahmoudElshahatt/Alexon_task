package com.shahtott.alexon_task.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    //This state gets triggered when the login call succeeded.
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _generalError = MutableLiveData<String>()
    val generalError: LiveData<String> = _generalError

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean> = _networkError

    private var _job: Job? = null

    fun loginClicked(phone: String, password: String) {
        _isLoading.value = true
        _job = viewModelScope.launch {
            //No Backend server for now.
            loginRepository.login(phone, password)
            _isSuccess.value = true
        }
        _isLoading.postValue(false)
    }

    fun cancelCurrentJob() {
        if (_job?.isActive == true) {
            _job?.cancel()
        }
    }

}