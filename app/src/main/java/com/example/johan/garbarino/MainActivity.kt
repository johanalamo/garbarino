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
import android.arch.lifecycle.Observer
import  com.example.johan.mvvm.MyViewModel
import android.arch.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

   private lateinit var recyclerView:RecyclerView
   private lateinit var viewAdapter: RecyclerView.Adapter<*>
   private lateinit var viewManager: RecyclerView.LayoutManager

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.layout_main_activity)
      Data.productListLoaded = false

      var viewModel:MyViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
      viewModel.getUsername().observe(this, 
               Observer { 
                  valor -> createRecyclerViewProductList(valor!!.items)
                  }
      )
      viewModel.getProductListData()
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
