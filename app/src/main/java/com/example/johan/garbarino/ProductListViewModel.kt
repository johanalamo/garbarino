package com.example.johan.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.johan.garbarino.Data
import com.example.johan.garbarino.ProductListService
import com.example.johan.garbarino.ProductListResponse
import android.os.SystemClock
//https://medium.com/rocknnull/exploring-kotlin-using-android-architecture-components-and-vice-versa-aa16e600041a




class ProductListViewModel : ViewModel() {
   private val productList = MutableLiveData<ProductListResponse>()

    fun loadProductListData() {
        /* expensive operation, e.g. network request */
//        username.value = "empezamos con getproduct list data"
         val retrofit = Retrofit.Builder()
            .baseUrl(Data.getUrlProductList())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
         val service = retrofit.create(ProductListService::class.java)
         val call = service.getProductListData()
         call.enqueue(object : retrofit2.Callback<ProductListResponse> {
            override fun onResponse(call: retrofit2.Call<ProductListResponse>, response: retrofit2.Response<ProductListResponse>) {
               if (response.code() == 200) {
                  Data.productList = response.body()!!
                  Data.productListLoaded = true
                  SystemClock.sleep(1000)
                  productList.value = Data.productList
               }
            }
            override fun onFailure(call: retrofit2.Call<ProductListResponse>, t: Throwable) {
               Data.productDetailsLoaded = false
            }
         })
    }
    fun getProductList(): LiveData<ProductListResponse> {
        return productList
    }
}

