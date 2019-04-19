package com.example.johan.garbarino

import retrofit2.Call
import retrofit2.http.GET

interface ProductListService {
    @GET (".")
    fun getProductListData(): Call<ProductListResponse>
}