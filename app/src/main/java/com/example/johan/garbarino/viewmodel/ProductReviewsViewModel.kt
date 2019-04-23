package com.example.johan.garbarino.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.johan.garbarino.*
import com.example.johan.garbarino.response.ProductReviewsResponse
import com.example.johan.garbarino.response.Review
import com.example.johan.garbarino.service.ProductReviewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductReviewsViewModel : ViewModel() {
   private val productReviews = MutableLiveData<ProductReviewsResponse>()

    fun loadProductReviewsData(productId:String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(ConfigApp.getUrlProductReviews(productId))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductReviewsService::class.java)
        val call = service.getProductReviewsData()
        call.enqueue(object : Callback<ProductReviewsResponse> {
            override fun onResponse(call: Call<ProductReviewsResponse>, response: Response<ProductReviewsResponse>) {
                println ("===================================================ProductReviewsViewModel:entra en response")
                if (response.code() == 200) {
                    productReviews.value = response.body()!!
                    println ("===================================================ProductReviewsViewModel: entra en response.code = 200")
                }
            }
            override fun onFailure(call: Call<ProductReviewsResponse>, t: Throwable) {
                println ("===================================================Error on connection on ProductReviewsViewModel")
            }
        })
    }
    fun getProductDetails(): LiveData<ProductReviewsResponse> {
        return productReviews
    }
    fun getReviewList(cantidad:Int? = null):ArrayList<Review>{
        var res:ArrayList<Review> = ArrayList()
        var max:Int = 0;

        if (cantidad == null)
            max = productReviews.value!!.items!![0]!!.reviews!!.size!! - 1
        else
            if (cantidad > productReviews.value!!.items!![0]!!.reviews!!.size!!)
                max = productReviews.value!!.items!![0]!!.reviews!!.size!! - 1
            else
                max = cantidad - 1
        for (i in 0..(max)) {
            try{
                res.add(productReviews.value!!.items!![0]!!.reviews!![i])
            }catch(e:Exception){
                print("pos invalida: " + i.toString() + "   " )
            }
        }
        return res
    }

}

