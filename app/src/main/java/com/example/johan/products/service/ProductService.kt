package com.example.johan.products.service

import com.example.johan.products.response.ProductDetailsResponse
import com.example.johan.products.response.ProductListResponse
import com.example.johan.products.response.ProductReviewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET(".")
    fun getProductListData(): Call<ProductListResponse>

    @GET("/{id}/")
    fun getProductDetailsData(@Query("id") productId: String): Call<ProductDetailsResponse>

    @GET("/{id}/reviews/")
    fun getProductReviewsData(@Query("id") productId: String): Call<ProductReviewsResponse>

}
