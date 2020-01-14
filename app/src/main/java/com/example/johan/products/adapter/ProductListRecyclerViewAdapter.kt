package com.example.johan.products.adapter

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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_product_list_view_holder, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.updateImageWithUrl("http:" + data[position].image_url)
        holder.view.txtDescription.text = data[position].description
        holder.view.txtPrice.text = context.getString(R.string.price, data[position].price)
        if (data[position].discount == 0)
            holder.view.lytDiscount.visibility = LinearLayout.GONE
        else {
            holder.view.txtListPrice.text =
                context.getString(R.string.price, data[position].list_price)
            holder.view.txtDiscount.text =
                context.getString(R.string.discount, data[position].discount)
            holder.view.txtListPrice.setPaintFlags(holder.view.txtListPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        }

        holder.view.setOnClickListener {
            clickListener.listItemClicked(data[position].id!!)
        }
    }

    override fun getItemCount() = data.size

    //internal objects

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val myImageView: ImageView = itemView.findViewById<ImageView>(R.id.imgProduct)
        private val TAG = ProductListRecyclerViewAdapter.ViewHolder::class.java.simpleName

        fun updateImageWithUrl(url: String) {
            Picasso.with(itemView.context).load(url).into(myImageView,
                object
                    : Callback {
                    override fun onSuccess() {}
                    override fun onError() {
                        Log.d(TAG, "********************error en la carga: " + url)
                    }
                }
            )
        }
    }


    interface ClickListener {
        fun listItemClicked(productId: String)
    }
}
