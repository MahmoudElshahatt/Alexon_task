package com.shahtott.alexon_task.auth.login

import com.shahtott.alexon_task.base.BaseRepository
import com.shahtott.alexon_task.data.local.User
import javax.inject.Inject

class LoginRepository @Inject constructor() : BaseRepository() {

    /*
    Here we suppose to make a network call
    with the login body and save the response if we have a server ;).
    */

    suspend fun login(email: String, password: String) {
        //Here we make the call and get the user data from server.

        val userDataFromRemote= User("Mahmoud Elshahatt","mahmoudelshahatt@gmail.com",null)
        saveUserDataAndLogFlag(userDataFromRemote)
    }

}