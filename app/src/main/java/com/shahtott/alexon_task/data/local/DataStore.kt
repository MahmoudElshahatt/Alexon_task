package com.shahtott.alexon_task.data.local

interface DataStore {

    suspend fun insertUser(user: User)

    suspend fun getUser(): User

    suspend fun insertToken(token: String)

    suspend fun getToken(): String

    suspend fun getUserName(): String

    suspend fun getUserTitle(): String

    suspend fun getUserImageUrl(): String

    suspend fun isLoggedIn(): Boolean

    suspend fun setLogged(isLogged: Boolean)

    suspend fun isPassedOnBoarding(): Boolean

    suspend fun setPassedOnBoarding(isPassed: Boolean)

    suspend fun clearAll()

}
