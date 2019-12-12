package com.example.johan.products

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
//import android.support.v7.widget.GridLayoutManager
//import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.RecyclerView
import android.arch.lifecycle.Observer
import  com.example.johan.products.viewmodel.ProductListViewModel
import android.arch.lifecycle.ViewModelProviders
import android.widget.Toast
import com.example.johan.products.adapter.ProductListRecyclerViewAdapter
import com.example.johan.products.response.Product

class ProductListActivity : AppCompatActivity() {

   private lateinit var recyclerView:RecyclerView

   private lateinit var viewModel:ProductListViewModel

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.layout_main_activity)

      viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
      viewModel.getProductList().observe(this,
               Observer {
                  productList -> createRecyclerViewProductList(productList!!.items)
                  }
      )
      viewModel.loadProductListData()
   }

   fun createRecyclerViewProductList(data:Array<Product>){
      //recyclerView.adapter = GridLayoutManager(this, 2)
      //recyclerView.adapter = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

      recyclerView = findViewById <RecyclerView>(R.id.rviewProducts)
      recyclerView.setHasFixedSize(false)
      recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
      recyclerView.adapter = ProductListRecyclerViewAdapter(data, this)
   }
}
