package com.example.johan.garbarino.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.johan.garbarino.ConfigApp
import com.example.johan.garbarino.service.ProductListService
import com.example.johan.garbarino.response.ProductListResponse

//https://medium.com/rocknnull/exploring-kotlin-using-android-architecture-components-and-vice-versa-aa16e600041a




class ProductListViewModel : ViewModel() {
   private val productList = MutableLiveData<ProductListResponse>()

    fun loadProductListData() {
        /* expensive operation, e.g. network request */
//        username.value = "empezamos con getproduct list data"
         val retrofit = Retrofit.Builder()
            .baseUrl(ConfigApp.getUrlProductList())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
         val service = retrofit.create(ProductListService::class.java)
         val call = service.getProductListData()
         call.enqueue(object : retrofit2.Callback<ProductListResponse> {
            override fun onResponse(call: retrofit2.Call<ProductListResponse>, response: retrofit2.Response<ProductListResponse>) {
               if (response.code() == 200) {
                  productList.value = response.body()!!
               }
            }
            override fun onFailure(call: retrofit2.Call<ProductListResponse>, t: Throwable) {
                println ("Error on connection on ProductListViewModel")
            }
         })
    }
    fun getProductList(): LiveData<ProductListResponse> {
        return productList
    }
}

