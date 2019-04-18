package com.example.johan.garbarino

import retrofit2.Call
import retrofit2.http.GET

interface ProductDetailsService {
    @GET (".")
    fun getProductDetailsData(): Call<ProductDetailsResponse>
}