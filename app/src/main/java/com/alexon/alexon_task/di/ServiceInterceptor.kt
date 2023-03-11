package com.alexon.alexon_task.di

import android.util.Log
import com.alexon.alexon_task.data.local.DataStoreImpl
import com.alexon.alexon_task.data.remote.NO_AUTH_HEADER
import com.alexon.alexon_task.data.remote.REFRESH_HEADER
import com.alexon.alexon_task.data.remote.RemoteData
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

const val BEARER = "Bearer"

@Singleton
class MyServiceInterceptor @Inject constructor(
    dataStoreManager: DataStoreImpl,
) : Interceptor {

    var remoteDataManager: RemoteData? = null

    var sessionAccessToken: String = ""
    var sessionRefreshToken: String = ""

    init {
        runBlocking {
            sessionAccessToken = dataStoreManager.getToken()
            sessionRefreshToken = dataStoreManager.getToken()
        }
    }

    fun setSessionToken(sessionToken: String) {
        this.sessionAccessToken = sessionToken
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        // @required  -> (Accept) header
        //requestBuilder.addHeader("Accept", "application/json")

        // adding Bearer token as needed
        val bearer = BEARER
        if (request.header(NO_AUTH_HEADER) == null) {
            if (sessionAccessToken.isNotEmpty()) {
                requestBuilder.addHeader("Authorization", "$bearer $sessionAccessToken")
            }
        }else if(request.header(REFRESH_HEADER) == null){
            if (sessionAccessToken.isNotEmpty()) {
                requestBuilder.addHeader("Authorization", "$bearer $sessionAccessToken")
            }

        } else {
            Log.i("AuthorizationHeader", "Not Added")
        }
        return chain.proceed(requestBuilder.build())
    }
}
