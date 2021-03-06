package com.example.johan.products

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Paint
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.example.johan.products.adapter.ProductImageListRecyclerViewAdapter
import com.example.johan.products.adapter.ProductReviewListRecyclerViewAdapter
import com.example.johan.products.response.Image
import com.example.johan.products.response.ProductDetailsResponse
import com.example.johan.products.response.ProductReviewsResponse
import com.example.johan.products.response.Review
import com.example.johan.products.viewmodel.ProductDetailsViewModel
import com.example.johan.products.viewmodel.ProductReviewsViewModel
import kotlinx.android.synthetic.main.layout_product_details.*

class ProductDetailsActivity : AppCompatActivity() {
    private val TAG = ProductDetailsActivity::class.java.simpleName
    private var productId: String = ""

    private lateinit var recyclerViewImage: RecyclerView
    private lateinit var recyclerViewReview: RecyclerView
    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    private lateinit var productReviewsViewModel: ProductReviewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_up, R.anim.slide_off)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_details_activity)

        try {
            this.productId = getIntent().getExtras()?.getString("p_product_id") ?: ""
        } catch (e: Exception) {
            this.productId = "0982a08485"
        }
        Log.d(TAG, "================ProductDetailsActivity.productId = " + this.productId)
        loadProductDetailsViewModel()
        loadProductReviewsViewModel()

    }

    private fun loadProductDetailsViewModel() {
        productDetailsViewModel =
            ViewModelProviders.of(this).get(ProductDetailsViewModel::class.java)
        productDetailsViewModel.getProductDetails().observe(
            this,
            Observer { productDetails -> showDetailsOnUi(productDetails!!) }
        )
        productDetailsViewModel.loadProductDetailsData(this.productId)
    }

    private fun loadProductReviewsViewModel() {
        Log.d(TAG, "===============================entro en productdetailsactivity.loadproduVM")
        productReviewsViewModel =
            ViewModelProviders.of(this).get(ProductReviewsViewModel::class.java)
        productReviewsViewModel.getProductDetails().observe(this,
            Observer { productReviews ->
                Log.d(
                    TAG,
                    "===============================entro en productdetailsactivity.loadproduVM.observer"
                )
                showReviewsOnUI(productReviews!!)
                createRecyclerViewReviewList(productReviewsViewModel.getReviewList(ConfigApp.commentsToShow))
            }
        )
        productReviewsViewModel.loadProductReviewsData(this.productId)
    }

    fun showReviewsOnUI(res: ProductReviewsResponse) {
        var max: Float = res.items!![0].reviewStatistics!!.average!!
        var showAnimationStarsThread: ShowAnimationStarsThread = ShowAnimationStarsThread(this, max)
        showAnimationStarsThread.start()
    }

    fun showDetailsOnUi(res: ProductDetailsResponse) {
        txtDescription.text = res.description!!
        txtPrice.text = getString(R.string.price, res.price)

        if (res.discount == 0)
            lytDiscount.visibility = LinearLayout.GONE
        else {
            txtListPrice.text = getString(R.string.price, res.listPrice)
            txtDiscount.text = getString(R.string.discount, res.discount)
            txtListPrice.setPaintFlags(txtListPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        }
        createRecyclerViewImageList(res.resources!!.images)
    }

    fun createRecyclerViewImageList(data: Array<Image>) {
        recyclerViewImage = findViewById<RecyclerView>(R.id.rviewProductListImages)

        recyclerViewImage.setHasFixedSize(false)
        recyclerViewImage.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewImage.adapter = ProductImageListRecyclerViewAdapter(data)
    }

    fun createRecyclerViewReviewList(data: ArrayList<Review>) {
        recyclerViewReview = findViewById<RecyclerView>(R.id.rviewProductListReviews)

        recyclerViewReview.setHasFixedSize(false);
        recyclerViewReview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewReview.adapter = ProductReviewListRecyclerViewAdapter(data, this)
    }
}

class ShowAnimationStarsThread(p_caller: AppCompatActivity, maxPoints: Float) : Thread() {
    var max: Float = maxPoints
    var caller: AppCompatActivity = p_caller
    val animationDuration: Float = 1000.toFloat()   //miliseguntos totales de la animación
    val framesPerSecond: Float = 60.toFloat()
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
