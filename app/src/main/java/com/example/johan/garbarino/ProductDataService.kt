package com.example.johan.garbarino

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDataService {
    @GET("data/2.5/weather?")
    fun getProductData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String): Call<ProductDataResponse>
}