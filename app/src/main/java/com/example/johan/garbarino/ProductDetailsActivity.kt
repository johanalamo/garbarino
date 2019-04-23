package com.example.johan.garbarino

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_product_details.*
import android.os.SystemClock
import com.example.johan.mvvm.ProductDetailsViewModel
import com.example.johan.mvvm.ProductReviewsViewModel

class DetailsActivity : AppCompatActivity() {
    private var productId: String = ""
    private var txtProductData: TextView? = null

    private lateinit var recyclerViewImage:RecyclerView
    private lateinit var viewAdapterImage: RecyclerView.Adapter<*>
    private lateinit var viewManagerImage: RecyclerView.LayoutManager

    private lateinit var recyclerViewReview:RecyclerView
    private lateinit var viewAdapterReview: RecyclerView.Adapter<*>
    private lateinit var viewManagerReview: RecyclerView.LayoutManager

    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    private lateinit var productReviewsViewModel: ProductReviewsViewModel



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

        productDetailsViewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel::class.java)
        productDetailsViewModel.getProductDetails().observe(this,
            Observer {
                    productDetails -> showDetailsOnUi(productDetails!!)
                    createRecyclerViewImageList(productDetails.resources!!.images)
            }
        )
        productDetailsViewModel.loadProductDetailsData(this.productId)



        productReviewsViewModel = ViewModelProviders.of(this).get(ProductReviewsViewModel::class.java)
        productReviewsViewModel.getProductDetails().observe(this,
            Observer { productReviews ->
                showReviewsOnUI(productReviews!!)
                createRecyclerViewReviewList(Data.getReviewList())
            }
        )
        productReviewsViewModel.loadProductReviewsData(this.productId)

    }


    fun showReviewsOnUI(res: ProductReviewsResponse) {
        var max:Float = res!!.items!![0]!!.reviewStatistics!!.average!!
        var showAnimationStarsThread:ShowAnimationStarsThread = ShowAnimationStarsThread(this, max)
        showAnimationStarsThread.start()
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
        viewAdapterImage = ProductImageListAdapter(data, this)
        recyclerViewImage = findViewById <RecyclerView>(R.id.rviewProductListImages).apply {
            setHasFixedSize(false);
            layoutManager = viewManagerImage
            adapter = viewAdapterImage
        }
    }
    fun createRecyclerViewReviewList(data:ArrayList<Review>){
        viewManagerReview = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewAdapterReview = ProductReviewListAdapter(data, this)
        recyclerViewReview = findViewById <RecyclerView>(R.id.rviewProductListReviews).apply {
            setHasFixedSize(false);
            layoutManager = viewManagerReview
            adapter = viewAdapterReview
        }
    }
}

class ShowAnimationStarsThread(p_caller:AppCompatActivity, puntajeMaximo:Float): Thread(){
    var max:Float = puntajeMaximo
    var caller:AppCompatActivity = p_caller
    val animationDuration:Float = 1000.toFloat()   //miliseguntos totales de la animación
    val framesPerSecond:Float = 60.toFloat()
    override fun run() {
        super.run()
        var totalFrames = animationDuration / 1000.toFloat() * framesPerSecond

        var sumByInterval: Float = max / totalFrames
        var interval = animationDuration / totalFrames

        for (i in 1..totalFrames.toInt()) {
            SystemClock.sleep(interval.toLong())
            caller.runOnUiThread {
                caller.txtEstrellas.text = String.format("%.2f", (sumByInterval * i))
                caller.rtbarProductDetails.rating = (sumByInterval * i)
            }
        }
    }
}
