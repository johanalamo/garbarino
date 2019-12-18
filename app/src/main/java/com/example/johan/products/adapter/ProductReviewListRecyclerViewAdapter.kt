package com.example.johan.products.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.support.v7.app.AppCompatActivity
import com.example.johan.products.R
import com.example.johan.products.response.Review
import kotlinx.android.synthetic.main.layout_product_review_list_recycler_view.view.*

import com.example.johan.products.viewholder.ProductReviewListRecyclerViewViewHolder


class ProductReviewListRecyclerViewAdapter(private val data: ArrayList<Review>, private val context:AppCompatActivity) :
    RecyclerView.Adapter<ProductReviewListRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductReviewListRecyclerViewViewHolder {
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_product_review_list_recycler_view, parent, false) as LinearLayout
        return ProductReviewListRecyclerViewViewHolder(linearLyt)
    }

    override fun onBindViewHolder(holder: ProductReviewListRecyclerViewViewHolder, position: Int) {
        holder.linearLyt.txtNumReview.text  = (position + 1).toString()
        holder.linearLyt.txtReviewStars.text  = data[position].rating.toString()
        holder.linearLyt.rtbarProductReview.rating = data[position].rating!!

        holder.linearLyt.txtReviewDate.text  = data[position].submissionTime?.subSequence(0,10)
		holder.linearLyt.txtReviewTitle.text  = data[position].title
        holder.linearLyt.txtReviewDescription.text  = data[position].reviewText

        holder.linearLyt.txtReviewNickname.text  = context.getString(R.string.commentAuthor,  data[position].userNickname )
    }

    override fun getItemCount() = data.size
}
