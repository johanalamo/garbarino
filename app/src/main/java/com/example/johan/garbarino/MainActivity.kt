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


data class Lista(val items: Array<Product>)


class MainActivity : AppCompatActivity() {

   private lateinit var recyclerView:RecyclerView
   private lateinit var viewAdapter: RecyclerView.Adapter<*>
   private lateinit var viewManager: RecyclerView.LayoutManager

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.layout_main_activity)
      getListProducts()
   }

   fun getListProducts() {
      val client = OkHttpClient()
      val request = Request.Builder().url(Data.getUrlProductList()).build()
      var z: Callback = object : Callback {
         override fun onFailure(call: Call, e: IOException) {
               println("error recovering data from server garbarino");
         }
         override fun onResponse(call: Call, response: Response) {
            Data.productListLoaded = true
            Data.listData = response.body()?.string()
            val gson = Gson()
            val productList : Lista = gson.fromJson(Data.listData, Lista::class.java)
            Data.productList = productList.items
         }
      }
      client.newCall(request).enqueue(z)
      checkListProducts()
   }


   fun checkListProducts(){
      if (Data.productListLoaded){
         createRecyclerView(Data.productList)
      }else{
         runOnUiThread {
            Toast.makeText(this, "Data not ready", Toast.LENGTH_SHORT).show()
            SystemClock.sleep(2000)
            checkListProducts()
         }
      }
   }
   fun createRecyclerView(data:Array<Product>){
      //  https://developer.android.com/guide/topics/ui/layout/recyclerview
      viewManager = GridLayoutManager(this, 2) //LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
      viewAdapter = AdapterProductList(data, this)
      recyclerView = findViewById <RecyclerView>(R.id.rviewProducts).apply {
         setHasFixedSize(false);
         layoutManager = viewManager
         adapter = viewAdapter
      }
   }
}
