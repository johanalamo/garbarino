package com.example.johan.products.response

import com.google.gson.annotations.SerializedName


data class ProductListResponse(
    @SerializedName("items")
    var items: Array<Product> = arrayOf()
)


data class Product(
    @SerializedName("id")
    var id: String? = ""
) {
    @SerializedName("description")
    val description: String? = ""

    @SerializedName("image_url")
    val image_url: String? = ""

    @SerializedName("price")
    val price: Int? = 0

    @SerializedName("list_price")
    val list_price: Int? = 0

    @SerializedName("discount")
    val discount: Int? = 0
}
