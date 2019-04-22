package com.example.johan.garbarino

import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_details_activity.*
import kotlinx.android.synthetic.main.layout_product_details.*
import kotlinx.android.synthetic.main.layout_product_list_recycler_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailsActivity : AppCompatActivity() {
    private var productId: String = ""
    private var txtProductData: TextView? = null

    private lateinit var recyclerViewImage:RecyclerView
    private lateinit var viewAdapterImage: RecyclerView.Adapter<*>
    private lateinit var viewManagerImage: RecyclerView.LayoutManager
    private lateinit var recyclerViewReview:RecyclerView
    private lateinit var viewAdapterReview: RecyclerView.Adapter<*>
    private lateinit var viewManagerReview: RecyclerView.LayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_up, R.anim.slide_off)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_details_activity)

        Data.productDetailsLoaded = false
        Data.productReviewsLoaded = false


        try {
            this.productId = getIntent().getExtras().getString("p_product_id")
        } catch (e: Exception) {
            this.productId = "0982a08485"
        }
        getProductDetailsData()
        getProductReviewsData()
    }

    internal fun getProductDetailsData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Data.getUrlProductDetails(this.productId))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductDetailsService::class.java)
        val call = service.getProductDetailsData()
        call.enqueue(object : Callback<ProductDetailsResponse> {
            override fun onResponse(call: Call<ProductDetailsResponse>, response: Response<ProductDetailsResponse>) {
                if (response.code() == 200) {
                    Data.productDetails = response.body()!!
                    Data.productDetailsLoaded = true
                    showDetailsOnUi(Data.productDetails)
                    createRecyclerViewImageList(Data.getCompleteImageList())

                }
            }
            override fun onFailure(call: Call<ProductDetailsResponse>, t: Throwable) {
                Data.productDetailsLoaded = false
            }
        })
    }

    internal fun getProductReviewsData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Data.getUrlProductReviews(this.productId))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductReviewsService::class.java)
        val call = service.getProductReviewsData()
        call.enqueue(object : Callback<ProductReviewsResponse> {
            override fun onResponse(call: Call<ProductReviewsResponse>, response: Response<ProductReviewsResponse>) {
                if (response.code() == 200) {
                    Data.productReviews = response.body()!!
                    Data.productReviewsLoaded = true
                    showReviewsOnUI(Data.productReviews)
                    createRecyclerViewReviewList(Data.getReviewList())
                }
            }
            override fun onFailure(call: Call<ProductReviewsResponse>, t: Throwable) {
                Data.productReviewsLoaded = false
            }
        })
    }

    fun showReviewsOnUI(res: ProductReviewsResponse) {
        txtEstrellas.text = res!!.items!![0]!!.reviewStatistics!!.average!!.toString()
        rtbarProductDetails.rating = res!!.items!![0]!!.reviewStatistics!!.average!!
    }

    fun showDetailsOnUi(res: ProductDetailsResponse) {
        txtDescription.text = res.description!!
        txtPrice.text = "$ " + res.price.toString()

        if (res.discount == 0)
            lytDiscount.visibility = LinearLayout.GONE
        else {
            txtListPrice.text = "$ " + res.listPrice.toString()
            txtDiscount.text = res.discount.toString() + "% OFF"
            txtListPrice.setPaintFlags(txtListPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        }
    }

    fun createRecyclerViewImageList(data:Array<Image>){
        viewManagerImage = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewAdapterImage = AdapterProductImageList(data, this)
        recyclerViewImage = findViewById <RecyclerView>(R.id.rviewProductListImages).apply {
            setHasFixedSize(false);
            layoutManager = viewManagerImage
            adapter = viewAdapterImage
        }
    }

    fun createRecyclerViewReviewList(data:ArrayList<Review>){
        viewManagerReview = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewAdapterReview = AdapterProductReviewList(data, this)
        recyclerViewReview = findViewById <RecyclerView>(R.id.rviewProductListReviews).apply {
            setHasFixedSize(false);
            layoutManager = viewManagerReview
            adapter = viewAdapterReview
        }
    }


}
