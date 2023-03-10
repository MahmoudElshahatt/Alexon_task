package com.shahtott.alexon_task.ui.auth.login

import com.shahtott.alexon_task.base.BaseRepository
import com.shahtott.alexon_task.data.local.User
import com.shahtott.alexon_task.ui.auth.models.LoginBody
import com.shahtott.alexon_task.util.network.ResultWrapper
import com.shahtott.alexon_task.util.network.safeApiCall
import javax.inject.Inject

class LoginRepository @Inject constructor() : BaseRepository() {


    suspend fun login(email: String, password: String): ResultWrapper<Any> {
        return safeApiCall {
            val result = remoteData.login(
                LoginBody(
                    username = email,
                    password = password,
                )
            )
            val user = with(result) {
                User(
                    name = "$firstName $lastName",
                    email = email,
                    token = token,
                    imageUrl = image
                )
            }
            saveUserDataAndLogFlag(user)
        }

    }

}