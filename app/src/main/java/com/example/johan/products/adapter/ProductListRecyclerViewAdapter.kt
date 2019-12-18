package com.example.johan.products.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout_product_list_recycler_view.view.*
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import com.example.johan.products.response.Product
import com.example.johan.products.R
import com.example.johan.products.viewholder.ProductListRecyclerViewViewHolder
import com.example.johan.products.listener.ProductListRecyclerViewClickListener

class ProductListRecyclerViewAdapter(private val data: Array<Product>,
	private val context:AppCompatActivity,
	private val clickListener:ProductListRecyclerViewClickListener) :
    RecyclerView.Adapter<ProductListRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListRecyclerViewViewHolder {
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_product_list_recycler_view, parent, false) as LinearLayout
        return ProductListRecyclerViewViewHolder(linearLyt)
    }

    override fun onBindViewHolder(holder: ProductListRecyclerViewViewHolder, position: Int) {
        holder.updateImageWithUrl("http:" + data[position].image_url)
        holder.linearLyt.txtDescription.text  = data[position].description
        holder.linearLyt.txtPrice.text        = context.getString(R.string.price, data[position].price)
        if (data[position].discount == 0)
            holder.linearLyt.lytDiscount.visibility = LinearLayout.GONE
        else {
            holder.linearLyt.txtListPrice.text = context.getString(R.string.price , data[position].list_price)
            holder.linearLyt.txtDiscount.text = context.getString(R.string.discount, data[position].discount)
            holder.linearLyt.txtListPrice.setPaintFlags(holder.linearLyt.txtListPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        }

        holder.linearLyt.setOnClickListener {
			clickListener.listItemClicked(data[position])
        }
    }
    override fun getItemCount() = data.size
}
