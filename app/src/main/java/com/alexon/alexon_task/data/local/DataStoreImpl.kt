package com.alexon.alexon_task.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

private val Context.dataStore by preferencesDataStore("user_data")

class DataStoreImpl(
    appContext: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DataStore {

    private val mDataStore by lazy {
        appContext.dataStore
    }

    companion object {
        const val USER_TOKEN_ACCESS = "userTokenAccess"
        const val USER_NAME = "userName"
        const val USER_PHONE = "userPhone"
        const val USER_EMAIL = "userEmail"
        const val USER_IMAGE_URL = "userImage"
        const val IS_PASSED_ON_BOARDING = "passedIntro"
        const val IS_LOGGED_IN = "loggedIn"
    }

    override suspend fun setLogged(isLogged: Boolean) {
        withContext(dispatcher) {
            mDataStore.edit { settings ->
                settings[booleanPreferencesKey(IS_LOGGED_IN)] = isLogged
            }
        }
    }

    override suspend fun isPassedOnBoarding(): Boolean = withContext(dispatcher) {
        mDataStore.data.map { settings ->
            settings[booleanPreferencesKey(IS_PASSED_ON_BOARDING)] ?: false
        }.first()
    }

    override suspend fun setPassedOnBoarding(isPassed: Boolean) {
        withContext(dispatcher) {
            mDataStore.edit { settings ->
                settings[booleanPreferencesKey(IS_PASSED_ON_BOARDING)] = isPassed
            }
        }
    }

    override suspend fun clearAll() {
        mDataStore.edit { it.clear() }
        setPassedOnBoarding(true)
    }

    override suspend fun insertUser(user: User) {
        val name = user.name ?: "No Name"
        val email = user.email ?: "No email"
        val phone = user.phone ?: "No phone"
        val token = user.token ?: "No token"
        val imageUrl = user.imageUrl ?: "No image"

        mDataStore.edit { settings ->
            settings[stringPreferencesKey(USER_NAME)] = name
            settings[stringPreferencesKey(USER_EMAIL)] = email
            settings[stringPreferencesKey(USER_PHONE)] = phone
            settings[stringPreferencesKey(USER_TOKEN_ACCESS)] = token
            settings[stringPreferencesKey(USER_IMAGE_URL)] = imageUrl
        }
    }

    override suspend fun getUser(): User = withContext(dispatcher) {
        var name: String? = null
        var email: String? = null
        var phone: String? = null
        var token: String? = null
        var imageUrl: String? = null

        mDataStore.data.map { settings ->
            name = settings[stringPreferencesKey(USER_NAME)]
            email = settings[stringPreferencesKey(USER_EMAIL)]
            phone = settings[stringPreferencesKey(USER_PHONE)]
            token = settings[stringPreferencesKey(USER_TOKEN_ACCESS)]
            imageUrl = settings[stringPreferencesKey(USER_IMAGE_URL)]

        }.first().toString()

        User(
            name = name,
            email = email,
            phone = phone,
            token = token,
            imageUrl = imageUrl
        )
    }


    override suspend fun insertToken(token: String) {
        withContext(dispatcher) {
            mDataStore.edit { settings ->
                settings[stringPreferencesKey(USER_TOKEN_ACCESS)] = token
            }
        }
    }

    override suspend fun getToken(): String = withContext(dispatcher) {
        mDataStore.data.map { settings ->
            settings[stringPreferencesKey(USER_TOKEN_ACCESS)]
        }.first().toString()
    }

    override suspend fun getUserName(): String = withContext(dispatcher) {
        mDataStore.data.map { settings ->
            settings[stringPreferencesKey(USER_NAME)]
        }.first().toString()
    }

    override suspend fun isLoggedIn(): Boolean = withContext(dispatcher) {
        mDataStore.data.map { settings ->
            settings[booleanPreferencesKey(IS_LOGGED_IN)]
        }.first() ?: false
    }


    override suspend fun getUserImageUrl(): String = withContext(dispatcher) {
        mDataStore.data.map { settings ->
            settings[stringPreferencesKey(USER_IMAGE_URL)]
        }.first().toString()
    }
}