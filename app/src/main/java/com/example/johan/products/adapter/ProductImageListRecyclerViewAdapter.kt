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
import kotlinx.android.synthetic.main.layout_product_image_list_view_holder.view.*


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
        Picasso.get().load("http:" + data[position].url).into(holder.view.imgListImageProduct)

    override fun getItemCount() = data.size

    //internal objects
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
