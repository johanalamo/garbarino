package com.example.johan.garbarino

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDataService {
    @GET (".")
    fun getProductData(): Call<ProductDataResponse>
}