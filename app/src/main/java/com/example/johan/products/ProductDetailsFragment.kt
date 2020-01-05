package com.example.johan.products

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PRODUCT_ID = "productId"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProductDetailsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProductDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailsFragment : Fragment() {

    private val TAG = ProductDetailsFragment::class.java.simpleName

    // TODO: Rename and change types of parameters
    private var productId: String? = null


    private lateinit var recyclerViewImage: RecyclerView
    private lateinit var recyclerViewReview: RecyclerView
    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    private lateinit var productReviewsViewModel: ProductReviewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(ARG_PRODUCT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_product_details, container, false)


        loadProductDetailsViewModel()
        loadProductReviewsViewModel()



        return view

    }

    private fun loadProductDetailsViewModel() {
        productDetailsViewModel =
            ViewModelProviders.of(this).get(ProductDetailsViewModel::class.java)
        productDetailsViewModel.getProductDetails().observe(
            this,
            Observer { productDetails -> showDetailsOnUi(productDetails!!) }
        )
        productDetailsViewModel.loadProductDetailsData(this.productId ?: "")
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
        view?.let {
            recyclerViewImage = it.findViewById<RecyclerView>(R.id.rviewProductListImages)

            recyclerViewImage.setHasFixedSize(false)
            recyclerViewImage.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewImage.adapter = ProductImageListRecyclerViewAdapter(data)
        }
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
        productReviewsViewModel.loadProductReviewsData(this.productId ?: "")
    }

    fun createRecyclerViewReviewList(data: ArrayList<Review>) {
        view?.let {
            recyclerViewReview = it.findViewById<RecyclerView>(R.id.rviewProductListReviews)

            recyclerViewReview.setHasFixedSize(false);
            recyclerViewReview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerViewReview.adapter = ProductReviewListRecyclerViewAdapter(data, context as AppCompatActivity)
        }
    }

    fun showReviewsOnUI(res: ProductReviewsResponse) {
        var max: Float = res.items!![0].reviewStatistics!!.average!!
        var showAnimationStarsThread: ShowAnimationStarsThread = ShowAnimationStarsThread(context as AppCompatActivity, max)
        showAnimationStarsThread.start()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param productId Parameter 1.
         * @return A new instance of fragment ProductDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(productId: String) =
            ProductDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PRODUCT_ID, productId)
                }
            }
    }
}
