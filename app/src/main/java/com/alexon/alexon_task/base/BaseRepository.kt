package com.alexon.alexon_task.base

import com.alexon.alexon_task.data.local.DataStoreImpl
import com.alexon.alexon_task.data.local.User
import com.alexon.alexon_task.data.remote.RemoteData
import javax.inject.Inject

open class BaseRepository() {

    @Inject
    lateinit var remoteData: RemoteData

    @Inject
    lateinit var dataStore: DataStoreImpl

    suspend fun getUserName(): String {
        return dataStore.getUserName()
    }

    suspend fun clearUserData() {
        dataStore.clearAll()
    }

    suspend fun saveUserDataAndLogFlag(user: User) {
        // save user data to data store
        dataStore.setLogged(true)
        dataStore.insertUser(user)
    }
}
