package com.alexon.alexon_task.ui.onboarding

import androidx.lifecycle.ViewModel
import com.alexon.alexon_task.data.local.DataStoreImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var dataStore: DataStoreImpl

    suspend fun setUserPassedOnBoarding() =
        dataStore.setPassedOnBoarding(true)


    suspend fun isUserLoggedIn(): Boolean =
        dataStore.isLoggedIn()


    suspend fun isOnBoardingFinished(): Boolean =
        dataStore.isPassedOnBoarding()

}