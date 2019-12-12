package com.example.johan.products.adapter

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
import com.example.johan.products.viewholder.ProductImageListRecyclerViewViewHolder

import com.example.johan.products.FakeData

class ProductImageListRecyclerViewAdapter(private val data: Array<Image>, private val context:AppCompatActivity) :
    RecyclerView.Adapter<ProductImageListRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ProductImageListRecyclerViewViewHolder {
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_product_image_list_recycler_view, parent, false) as LinearLayout
        return ProductImageListRecyclerViewViewHolder(linearLyt)
    }

    override fun onBindViewHolder(holder: ProductImageListRecyclerViewViewHolder, position: Int) = holder.updateImageWithUrl("http:" + data[position].url, context)

    override fun getItemCount() = data.size
}
