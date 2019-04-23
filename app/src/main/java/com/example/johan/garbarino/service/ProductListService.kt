package com.example.johan.garbarino.service

import com.example.johan.garbarino.response.ProductListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductListService {
    @GET (".")
    fun getProductListData(): Call<ProductListResponse>
}