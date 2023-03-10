package com.shahtott.alexon_task.ui.main.products

import com.shahtott.alexon_task.base.BaseRepository
import com.shahtott.alexon_task.ui.main.products.models.ProductsResponse
import javax.inject.Inject

class ProductsRepository @Inject constructor() : BaseRepository() {

    suspend fun getProducts():ProductsResponse {
     return remoteData.getProducts()
    }
}