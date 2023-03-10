package com.shahtott.alexon_task.base

import com.shahtott.alexon_task.data.local.DataStoreImpl
import com.shahtott.alexon_task.data.local.User
import javax.inject.Inject

open class BaseRepository() {

    @Inject
    lateinit var dataStore: DataStoreImpl


    suspend fun getUserName(): String {
        return dataStore.getUserName()
    }

    suspend fun saveUserDataAndLogFlag(user: User) {
        // save user data to data store
        dataStore.setLogged(true)
        dataStore.insertUser(user)
    }
}
