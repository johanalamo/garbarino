package com.example.johan.garbarino


import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.layout_test.*

import com.example.johan.mvvm.*

class TestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_test)
         
      /* Called on Activity onCreate() */
      var viewModel:ProductListViewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
      viewModel.getProductList().observe(this,
               Observer { 
                  valor -> updateUI(valor) 
                  }
      )
      /*  Called if there is no active network request */
//      viewModel.initNetworkRequest()
      btnTest.setOnClickListener {
               viewModel.loadProductListData()
         }
   }
   
   fun updateUI(product:ProductListResponse?){
      var cant:Int = product!!.items.size;
      var s:String = "";
      for (i in 0..(cant -1)){
         s += product!!.items[i].description + "\n\n"
      }
      
      txtTexto.text = "valor obtenido: \n" + s
   }
   
}
