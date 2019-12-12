package com.example.johan.products.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ImageView
import com.squareup.picasso.Picasso;
import android.support.v7.app.AppCompatActivity
import com.example.johan.products.response.Image
import com.example.johan.products.R
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.layout_product_image_list_recycler_view.view.*

import com.example.johan.products.FakeData

class ProductImageListRecyclerViewViewHolder(val linearLyt: LinearLayout) : RecyclerView.ViewHolder(linearLyt){
    private val myImageView: ImageView = itemView.findViewById<ImageView>(R.id.imgListImageProduct)
    fun updateImageWithUrl(url: String, c:AppCompatActivity) = Picasso.with(itemView.context).load(url).into(myImageView,
        object
            : Callback {
            override fun onSuccess() { }
            override fun onError() {
                linearLyt.imgListImageProduct.setImageDrawable(FakeData.getFakeDrawableFor(c, url))
                println("*********************************************ListImage: error on internet connection, loaded from local: " + url)
            }
        })
}
