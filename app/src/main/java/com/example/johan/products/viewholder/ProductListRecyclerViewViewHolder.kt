package com.example.johan.products.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout_product_list_recycler_view.view.*
import android.graphics.*
import android.widget.ImageView
import com.squareup.picasso.Picasso;
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.johan.products.response.Product
import com.example.johan.products.ProductDetailsActivity
import com.example.johan.products.R
import com.squareup.picasso.Callback

import com.example.johan.products.FakeData


class ProductListRecyclerViewViewHolder(val linearLyt: LinearLayout) : RecyclerView.ViewHolder(linearLyt) {
    private val myImageView: ImageView = itemView.findViewById<ImageView>(R.id.imgProduct)

    fun updateImageWithUrl(url: String, c:AppCompatActivity) {
     Picasso.with(itemView.context).load(url).into(myImageView,
         object
             : Callback {
             override fun onSuccess() {                 }
             override fun onError() {
                 linearLyt.imgProduct.setImageDrawable(FakeData.getFakeDrawableFor(c, url))
                 println("********************error en la carga: " + url)
             }
         }
        )
      }
}
