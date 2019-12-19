package com.example.johan.products.response

import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    @SerializedName("xid")
    var xid: String? = null
) {
    @SerializedName("description")
    var description: String? = null

    @SerializedName("list_price")
    var listPrice: Int? = 0

    @SerializedName("price")
    var price: Int? = 0

    @SerializedName("discount")
    var discount: Int? = 0

    @SerializedName("main_image")
    var mainImage: Image? = null

    @SerializedName("resources")
    var resources: Resources? = null
}

data class Image(
    @SerializedName("max_width")
    var maxWidth: Int = 0
) {
    @SerializedName("url")
    var url: String? = null
}

data class Resources(
    @SerializedName("images")
    var images: Array<Image> = arrayOf()
)
