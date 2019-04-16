package com.example.johan.garbarino

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_details_activity.*

class DetailsActivity : AppCompatActivity() {
   private var productId:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_details_activity)
        this.productId = getIntent().getExtras().getString("p_product_id")      
        txtMessage.text = "ProductId: " + this.productId + "\n\n" + " Módulo en construcción"
    }
}
