package com.example.johan.products.adapter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.johan.products.R
import com.example.johan.products.response.Image
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class ProductImageListRecyclerViewAdapter(
    private val data: Array<Image>,
    private val context: AppCompatActivity
) :
    RecyclerView.Adapter<ProductImageListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.layout_product_image_list_recycler_view,
                parent,
                false
            ) as LinearLayout
        return ViewHolder(linearLyt)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.updateImageWithUrl("http:" + data[position].url)

    override fun getItemCount() = data.size

    //internal objects
    class ViewHolder(val linearLyt: LinearLayout) : RecyclerView.ViewHolder(linearLyt) {
        private val TAG = ViewHolder::class.java.simpleName
        private val myImageView: ImageView =
            itemView.findViewById<ImageView>(R.id.imgListImageProduct)

        fun updateImageWithUrl(url: String) =
            Picasso.with(itemView.context).load(url).into(myImageView,
                object
                    : Callback {
                    override fun onSuccess() {}
                    override fun onError() {
                        Log.d(
                            TAG,
                            "*********************************************ListImage: error on internet connection: " + url
                        )
                    }
                })
    }
}
