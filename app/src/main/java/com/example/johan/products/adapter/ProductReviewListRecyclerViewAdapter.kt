package com.example.johan.products.adapter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.johan.products.R
import com.example.johan.products.response.Review
import kotlinx.android.synthetic.main.layout_product_review_list_recycler_view.view.*


class ProductReviewListRecyclerViewAdapter(
    private val data: ArrayList<Review>,
    private val context: AppCompatActivity
) :
    RecyclerView.Adapter<ProductReviewListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.layout_product_review_list_recycler_view,
                parent,
                false
            ) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.txtNumReview.text = (position + 1).toString()
        holder.view.txtReviewStars.text = data[position].rating.toString()
        holder.view.rtbarProductReview.rating = data[position].rating!!

        holder.view.txtReviewDate.text = data[position].submissionTime?.subSequence(0, 10)
        holder.view.txtReviewTitle.text = data[position].title
        holder.view.txtReviewDescription.text = data[position].reviewText

        holder.view.txtReviewNickname.text =
            context.getString(R.string.commentAuthor, data[position].userNickname)
    }

    override fun getItemCount() = data.size

    //internal objects
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
