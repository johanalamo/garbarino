package com.example.johan.products.viewholder

import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.ImageView
import com.squareup.picasso.Picasso;
import com.example.johan.products.R
import com.squareup.picasso.Callback
import android.util.Log

class ProductImageListRecyclerViewViewHolder(val linearLyt: LinearLayout) : RecyclerView.ViewHolder(linearLyt){
	private val TAG = ProductImageListRecyclerViewViewHolder::class.java.simpleName
    private val myImageView: ImageView = itemView.findViewById<ImageView>(R.id.imgListImageProduct)
    fun updateImageWithUrl(url: String) = Picasso.with(itemView.context).load(url).into(myImageView,
        object
            : Callback {
            override fun onSuccess() { }
            override fun onError() {
                Log.d(TAG, "*********************************************ListImage: error on internet connection: " + url)
            }
        })
}
