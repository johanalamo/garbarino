package com.example.johan.garbarino.service

import com.example.johan.garbarino.response.ProductDetailsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductDetailsService {
    @GET (".")
    fun getProductDetailsData(): Call<ProductDetailsResponse>
}