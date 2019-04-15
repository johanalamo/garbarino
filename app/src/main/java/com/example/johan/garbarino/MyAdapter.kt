package com.example.johan.garbarino

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.my_text_view.view.*

class MyAdapter(private val data: Array<Product>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val linearLyt: LinearLayout) : RecyclerView.ViewHolder(linearLyt)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        // create a new view
        val linearLyt = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_text_view, parent, false) as LinearLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(linearLyt)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.linearLyt.txtId.text           = data[position].id
        holder.linearLyt.txtDescription.text  = data[position].description
        holder.linearLyt.txtPrice.text        = "$ " + data[position].price.toString()
        holder.linearLyt.txtListPrice.text    = "$ " + data[position].list_price.toString()
        holder.linearLyt.txtDiscount.text     = data[position].discount.toString() + "% OFF"
        holder.linearLyt.setOnClickListener({
            println("Id presionado:  "  + data[position].id)
        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size
}
