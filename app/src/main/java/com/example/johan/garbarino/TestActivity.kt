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
      var viewModel:MyViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
      viewModel.getUsername().observe(this, Observer { valor -> updateUI(valor) })

      /*  Called if there is no active network request */
      viewModel.initNetworkRequest()       

      btnTest.setOnClickListener {
               viewModel.initNetworkRequestDos()
         }

   }
   
   
   fun updateUI(s:String?){
      txtTexto.text = "valor obtenido: " + s
   }
   
}