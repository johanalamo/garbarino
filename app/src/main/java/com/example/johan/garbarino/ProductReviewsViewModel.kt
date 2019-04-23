package com.example.johan.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.johan.garbarino.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//https://medium.com/rocknnull/exploring-kotlin-using-android-architecture-components-and-vice-versa-aa16e600041a

class ProductReviewsViewModel : ViewModel() {
   private val productReviews = MutableLiveData<ProductReviewsResponse>()

    fun loadProductReviewsData(productId:String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Data.getUrlProductReviews(productId))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductReviewsService::class.java)
        val call = service.getProductReviewsData()
        call.enqueue(object : Callback<ProductReviewsResponse> {
            override fun onResponse(call: Call<ProductReviewsResponse>, response: Response<ProductReviewsResponse>) {
                if (response.code() == 200) {
                    Data.productReviews = response.body()!!
                    Data.productReviewsLoaded = true
                    productReviews.value = Data.productReviews
                }
            }
            override fun onFailure(call: Call<ProductReviewsResponse>, t: Throwable) {
                Data.productReviewsLoaded = false
            }
        })
    }
    fun getProductDetails(): LiveData<ProductReviewsResponse> {
        return productReviews
    }
}

