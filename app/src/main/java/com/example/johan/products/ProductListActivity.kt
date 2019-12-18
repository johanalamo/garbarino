package com.example.johan.products

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.RecyclerView
import android.arch.lifecycle.Observer
import com.example.johan.products.viewmodel.ProductListViewModel
import android.arch.lifecycle.ViewModelProviders
import android.widget.Toast
import com.example.johan.products.adapter.ProductListRecyclerViewAdapter
import com.example.johan.products.response.Product
import com.example.johan.products.listener.ProductListRecyclerViewClickListener

class ProductListActivity : AppCompatActivity(), ProductListRecyclerViewClickListener {

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
      recyclerView = findViewById <RecyclerView>(R.id.rviewProducts)
      recyclerView.setHasFixedSize(false)
      recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
      recyclerView.adapter = ProductListRecyclerViewAdapter(data, this, this)
   }
   override fun listItemClicked(element:Product){
	   val intent: Intent = Intent(this, ProductDetailsActivity::class.java)
	   intent.putExtra("p_product_id", element.id)
	   this.startActivity(intent)
   }
}
