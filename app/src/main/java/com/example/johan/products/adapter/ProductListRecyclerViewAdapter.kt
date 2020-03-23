package com.example.johan.products.adapter

import android.databinding.DataBindingUtil
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.johan.products.R
import com.example.johan.products.databinding.LayoutProductListViewHolderBinding
import com.example.johan.products.response.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_product_list_view_holder.view.*

class ProductListRecyclerViewAdapter(
    private val data: Array<Product>,
    private val context: AppCompatActivity,
    private val clickListener: ClickListener
) :
    RecyclerView.Adapter<ProductListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutProductListViewHolderBinding>(layoutInflater,
            R.layout.layout_product_list_view_holder, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val product = data[position]
        holder.binding.product = product

        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(data[position].id!!)
        }
        Picasso.get().load("http:" + data[position].image_url).into(holder.itemView.imgProduct)
        //this is the way to put a strike text
        // holder.view.txtListPrice.setPaintFlags(holder.view.txtListPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
    }

    override fun getItemCount() = data.size

    //internal objects
    class ViewHolder(val binding: LayoutProductListViewHolderBinding):RecyclerView.ViewHolder(binding.root)

    interface ClickListener {
        fun listItemClicked(productId: String)
    }
}
