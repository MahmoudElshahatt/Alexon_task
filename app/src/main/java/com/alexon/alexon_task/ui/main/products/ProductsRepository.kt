package com.alexon.alexon_task.ui.main.products

import com.alexon.alexon_task.base.BaseRepository
import com.alexon.alexon_task.ui.main.products.models.ProductsResponse
import com.alexon.alexon_task.util.network.ResultWrapper
import com.alexon.alexon_task.util.network.safeApiCall
import javax.inject.Inject

class ProductsRepository @Inject constructor() : BaseRepository() {

    suspend fun getProducts(): ResultWrapper<ProductsResponse> {
        return safeApiCall {
            return@safeApiCall remoteData.getProducts()
        }
    }
}