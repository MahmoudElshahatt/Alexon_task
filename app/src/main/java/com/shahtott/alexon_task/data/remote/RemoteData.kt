package com.shahtott.alexon_task.data.remote

import com.shahtott.alexon_task.main.products.models.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteData {

    @GET("/products")
    suspend fun getProducts(): ProductsResponse

//    @GET("/products/{productId}")
//    suspend fun getProducts(@Path("productId") id: Int):

}