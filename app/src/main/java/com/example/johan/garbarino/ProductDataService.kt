package com.example.johan.garbarino

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDataService {
    @GET("data/2.5/weather?lat=35&lon=13")
    fun getProductData(@Query("APPID") app_id: String): Call<ProductDataResponse>
}