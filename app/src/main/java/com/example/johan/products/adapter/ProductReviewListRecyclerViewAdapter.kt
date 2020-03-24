package com.example.johan.products.adapter

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.johan.products.R
import com.example.johan.products.databinding.LayoutProductReviewListViewHolderBinding
import com.example.johan.products.response.Review
import kotlinx.android.synthetic.main.layout_product_review_list_view_holder.view.*


class ProductReviewListRecyclerViewAdapter(
    private val data: ArrayList<Review>,
    private val context: AppCompatActivity
) :
    RecyclerView.Adapter<ProductReviewListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutProductReviewListViewHolderBinding>(layoutInflater,
            R.layout.layout_product_review_list_view_holder, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = data[position]
        holder.binding.review = review
        holder.binding.position = (position + 1)
    }

    override fun getItemCount() = data.size

    //internal objects
    class ViewHolder(val binding: LayoutProductReviewListViewHolderBinding): RecyclerView.ViewHolder(binding.root)
}
