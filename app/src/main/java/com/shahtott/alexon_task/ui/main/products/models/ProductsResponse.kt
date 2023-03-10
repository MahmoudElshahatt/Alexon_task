package com.shahtott.alexon_task.ui.main.products.models


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class ProductsResponse(
    @field:Json(name = "limit")
    val limit: Int? = 0, // 30
    @field:Json(name = "products")
    val products: List<Product?>? = listOf(),
    @field:Json(name = "skip")
    val skip: Int? = 0, // 0
    @field:Json(name = "total")
    val total: Int? = 0 // 100
) {
    @Keep
    data class Product(
        @field:Json(name = "brand")
        val brand: String? = "", // Apple
        @field:Json(name = "category")
        val category: String? = "", // smartphones
        @field:Json(name = "description")
        val description: String? = "", // An apple mobile which is nothing like apple
        @field:Json(name = "discountPercentage")
        val discountPercentage: Double? = 0.0, // 12.96
        @field:Json(name = "id")
        val id: Int? = 0, // 1
        @field:Json(name = "images")
        val images: List<String?>? = listOf(),
        @field:Json(name = "price")
        val price: Int? = 0, // 549
        @field:Json(name = "rating")
        val rating: Double? = 0.0, // 4.69
        @field:Json(name = "stock")
        val stock: Int? = 0, // 94
        @field:Json(name = "thumbnail")
        val thumbnail: String? = "", // https://i.dummyjson.com/data/products/1/thumbnail.jpg
        @field:Json(name = "title")
        val title: String? = "" // iPhone 9
    )
}