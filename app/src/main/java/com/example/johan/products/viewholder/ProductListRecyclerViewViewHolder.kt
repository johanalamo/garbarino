package com.example.johan.products.viewholder

import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.ImageView
import com.squareup.picasso.Picasso;
import com.example.johan.products.R
import com.squareup.picasso.Callback
import android.util.Log

class ProductListRecyclerViewViewHolder(val linearLyt: LinearLayout) : RecyclerView.ViewHolder(linearLyt) {
    private val myImageView: ImageView = itemView.findViewById<ImageView>(R.id.imgProduct)
	private val TAG = ProductListRecyclerViewViewHolder::class.java.simpleName
	
    fun updateImageWithUrl(url: String) {
     Picasso.with(itemView.context).load(url).into(myImageView,
         object
             : Callback {
             override fun onSuccess() {                 }
             override fun onError() {
        		Log.d(TAG, "********************error en la carga: " + url)
             }
         }
        )
      }
}
