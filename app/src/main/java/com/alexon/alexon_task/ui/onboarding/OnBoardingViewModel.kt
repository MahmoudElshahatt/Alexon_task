package com.alexon.alexon_task.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexon.alexon_task.data.local.DataStoreImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val dataStore: DataStoreImpl) : ViewModel() {

    private val _userPassedOnBoarding = MutableLiveData<Boolean>()
    val userPassedOnBoarding: LiveData<Boolean> = _userPassedOnBoarding

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean> = _isUserLoggedIn

    private val _startOnBoarding = MutableLiveData<Boolean>()
    val startOnBoarding: LiveData<Boolean> = _startOnBoarding

    init {
        handleUserDirections()
    }

    private fun handleUserDirections() {
        viewModelScope.launch {
            if (isOnBoardingFinished() && !isUserLoggedIn()) {
                _userPassedOnBoarding.value = true
            } else if (isUserLoggedIn()) {
                _isUserLoggedIn.value = true
            } else {
                _startOnBoarding.value = true
            }
        }
    }

    suspend fun setUserPassedOnBoarding() =
        dataStore.setPassedOnBoarding(true)


    private suspend fun isUserLoggedIn(): Boolean =
        dataStore.isLoggedIn()


    private suspend fun isOnBoardingFinished(): Boolean =
        dataStore.isPassedOnBoarding()


}