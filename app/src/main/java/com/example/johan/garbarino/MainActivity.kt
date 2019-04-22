package com.example.johan.garbarino

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import java.io.IOException
import android.widget.Toast
import android.os.SystemClock
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {

   private lateinit var recyclerView:RecyclerView
   private lateinit var viewAdapter: RecyclerView.Adapter<*>
   private lateinit var viewManager: RecyclerView.LayoutManager

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.layout_main_activity)
      Data.productListLoaded = false
      getProductListData()
   }

   internal fun getProductListData() {
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
               createRecyclerViewProductList(Data.productList.items)
            }
         }
         override fun onFailure(call: retrofit2.Call<ProductListResponse>, t: Throwable) {
            Data.productDetailsLoaded = false
         }
      })
   }


   fun createRecyclerViewProductList(data:Array<Product>){
      viewManager = GridLayoutManager(this, 2) //LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
      viewAdapter = AdapterProductList(data, this)
      recyclerView = findViewById <RecyclerView>(R.id.rviewProducts).apply {
         setHasFixedSize(false);
         layoutManager = viewManager
         adapter = viewAdapter
      }
   }
}
