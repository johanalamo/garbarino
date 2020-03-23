package com.example.johan.products.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.johan.products.R
import com.example.johan.products.response.Image
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class ProductImageListRecyclerViewAdapter(
    private val data: Array<Image>
) :
    RecyclerView.Adapter<ProductImageListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.layout_product_image_list_view_holder,
                parent,
                false
            ) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.updateImageWithUrl("http:" + data[position].url)

    override fun getItemCount() = data.size

    //internal objects
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val TAG = ViewHolder::class.java.simpleName
        private val myImageView: ImageView =
            itemView.findViewById<ImageView>(R.id.imgListImageProduct)

        fun updateImageWithUrl(url: String) =
            Picasso.get().load(url).into(myImageView)
    }
}
