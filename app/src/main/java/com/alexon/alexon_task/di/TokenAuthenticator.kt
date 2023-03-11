package com.alexon.alexon_task.di

import com.alexon.alexon_task.data.local.DataStoreImpl
import com.alexon.alexon_task.data.remote.REFRESH_HEADER
import com.alexon.alexon_task.data.remote.RemoteData
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenAuthenticator @Inject constructor() : Authenticator {

    var remoteData: RemoteData? = null

    @Inject
    lateinit var dataStoreManager: DataStoreImpl

    @Inject
    lateinit var myServiceInterceptor: MyServiceInterceptor

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentAccessToken = myServiceInterceptor.sessionAccessToken

        if (response.request.header(REFRESH_HEADER) != null && response.code == 401)
            return null

        if (response.request.header(REFRESH_HEADER) != null && response.code == 200)
            return null

        if (remoteData != null && response.request.header("Authorization") != null) {

            val bearer = BEARER
            // Refresh your access_token using a synchronous api request
            val currentRefreshToken = myServiceInterceptor.sessionRefreshToken
            val newToken = myServiceInterceptor.sessionAccessToken
            if (newToken != currentAccessToken) {
                return response.request.newBuilder()
                    .header("Authorization", "$bearer $newToken")
                    .build()
            }

            try {
                val newAccessTokenCall = runBlocking {
                    remoteData?.refreshToken(
                        currentRefreshToken
                    )
                }

                try {
                    val updatedToken = newAccessTokenCall ?: ""
                    runBlocking {
                        dataStoreManager.insertToken(updatedToken)
                        myServiceInterceptor.setSessionToken(updatedToken)
                    }
                    return response.request.newBuilder()
                        .header("Authorization", "$bearer $updatedToken")
                        .build()
                } catch (E: Exception) {
                    return null
                }

            }catch (E: Exception) {
                return null
            }



        } else return null
    }

}
