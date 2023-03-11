package com.alexon.alexon_task.data.remote

import com.alexon.alexon_task.ui.auth.models.LoginBody
import com.alexon.alexon_task.ui.auth.models.LoginResponse
import com.alexon.alexon_task.ui.main.products.models.ProductsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

const val AUTH_HEADER = "Content-type:application/json"
interface RemoteData {

    @POST("/auth/login")
    @Headers(AUTH_HEADER)
    suspend fun login(@Body loginBody: LoginBody): LoginResponse

    @GET("/products")
    suspend fun getProducts(): ProductsResponse


//    @GET("/products/{productId}")
//    suspend fun getProducts(@Path("productId") id: Int):

}