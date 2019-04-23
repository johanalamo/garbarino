package com.example.johan.garbarino.service

import com.example.johan.garbarino.response.ProductReviewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductReviewsService {
    @GET (".")
    fun getProductReviewsData(): Call<ProductReviewsResponse>
}