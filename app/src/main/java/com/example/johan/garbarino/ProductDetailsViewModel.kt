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

class ProductDetailsViewModel : ViewModel() {
   private val productDetails = MutableLiveData<ProductDetailsResponse>()

    fun loadProductDetailsData(productId:String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Data.getUrlProductDetails(productId))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductDetailsService::class.java)
        val call = service.getProductDetailsData()
        call.enqueue(object : Callback<ProductDetailsResponse> {
            override fun onResponse(call: Call<ProductDetailsResponse>, response: Response<ProductDetailsResponse>) {
                if (response.code() == 200) {
                    Data.productDetails = response.body()!!
                    Data.productDetailsLoaded = true
                    productDetails.value = Data.productDetails
//                    showDetailsOnUi(Data.productDetails)
//                    createRecyclerViewImageList(Data.getCompleteImageList())

                }
            }
            override fun onFailure(call: Call<ProductDetailsResponse>, t: Throwable) {
                Data.productDetailsLoaded = false
            }
        })
    }
    fun getProductDetails(): LiveData<ProductDetailsResponse> {
        return productDetails
    }
}

