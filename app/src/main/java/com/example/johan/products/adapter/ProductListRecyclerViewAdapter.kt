package com.example.johan.products.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout_product_list_recycler_view.view.*
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.example.johan.products.response.Product
import com.example.johan.products.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ProductListRecyclerViewAdapter(private val data: Array<Product>,
	private val context:AppCompatActivity,
	private val clickListener:ProductListRecyclerViewAdapter.ClickListener) :
    RecyclerView.Adapter<ProductListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListRecyclerViewAdapter.ViewHolder {
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_product_list_recycler_view, parent, false) as LinearLayout
        return ProductListRecyclerViewAdapter.ViewHolder(linearLyt)
    }

    override fun onBindViewHolder(holder: ProductListRecyclerViewAdapter.ViewHolder, position: Int) {
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

    //internal objects

    class ViewHolder(val linearLyt: LinearLayout) : RecyclerView.ViewHolder(linearLyt) {
        private val myImageView: ImageView = itemView.findViewById<ImageView>(R.id.imgProduct)
        private val TAG = ProductListRecyclerViewAdapter.ViewHolder::class.java.simpleName

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


    interface ClickListener {
        fun listItemClicked(element:Product)
    }
}
