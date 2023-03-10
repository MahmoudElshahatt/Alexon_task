package com.shahtott.alexon_task.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahtott.alexon_task.data.local.DataStoreImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var dataStore: DataStoreImpl

    fun setUserPassedOnBoarding() {
        viewModelScope.launch {
            dataStore.setPassedOnBoarding(true)
        }
    }

    suspend fun isUserLoggedIn(): Boolean =
        dataStore.isLoggedIn()


    suspend fun isOnBoardingFinished(): Boolean =
        dataStore.isPassedOnBoarding()

}