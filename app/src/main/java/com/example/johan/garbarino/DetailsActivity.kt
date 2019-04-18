package com.example.johan.garbarino

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_details_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://www.c-sharpcorner.com/article/how-to-use-retrofit-2-with-android-using-kotlin/

class DetailsActivity : AppCompatActivity() {
   private var productId:String = ""
    private var txtProductData: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_details_activity)
        try {
            this.productId = getIntent().getExtras().getString("p_product_id")
        }catch (e:Exception) {
            this.productId = "0982a08485"
        }
        txtMessage.text = "ProductId: " + this.productId + "\n\n" + " Módulo en construcción"

        txtProductData = findViewById(R.id.textView)
  //      findViewById<View>(R.id.button).setOnClickListener {
        getProductDetailsData()
        getProductReviewsData()
 //         }

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
                    val productResponseDos = response.body()!!

                    Data.productDetails = productResponseDos
                    val productResponse = Data.productDetails
                    var stringBuilder = "\n\nDetails\nXID: " + productResponse.xid!! + "\n" +
                            "Description: " + productResponse.description!! + "\n" +
                            "price: " + productResponse!!.price!!.toString() + "\n" +
                            "list price: " + productResponse!!.listPrice!!.toString() + "\n" +
                            "discount: " + productResponse!!.discount!!.toString() + "\n" +
                            "mainIMage.maxWidth: " + productResponse.mainImage!!.maxWidth!! + "\n" +
                            "mainImage.url: " + productResponse.mainImage!!.url!!

                    for ( i in 0..(productResponse.resources!!.images.size - 1) ){
                        stringBuilder += "\n\n" + "im" + i.toString() + ": " + productResponse.resources!!.images[i].maxWidth
                        stringBuilder += "\n" + "im" + i.toString() + ": " + productResponse.resources!!.images[i].url

                    }

                    txtProductData!!.text = txtProductData!!.text.toString() + stringBuilder
                }
            }

            override fun onFailure(call: Call<ProductDetailsResponse>, t: Throwable) {
                txtProductData!!.text = t.message
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
                    val productResponseDos = response.body()!!

                    Data.productReviews = productResponseDos
                    val productResponse = Data.productReviews
                    var stringBuilder = "\n\nREVIEWS\nID: " + productResponse!!.items!![0].id!! + "\n"  +
                            "Estrellas: " + productResponse!!.items!![0]!!.reviewStatistics!!.average!!.toString() + "\n"
                    var max:Int = 3;
                    if (productResponse!!.items!![0]!!.reviews!!.size < 3) {
                        max = productResponse!!.items!![0]!!.reviews!!.size!!
                    }
                    for ( i in 0..( max - 1) ){
                        stringBuilder += "\n\n" + "name: " + productResponse!!.items!![0].reviews!![i].userNickname!!
                        stringBuilder += "\n" + "title: " + productResponse!!.items!![0].reviews!![i].title!!
                        stringBuilder += "\n" + "comment: " + productResponse!!.items!![0].reviews!![i].reviewText!!
                        stringBuilder += "\n" + "puntos: " + productResponse!!.items!![0].reviews!![i].rating!!.toString()

                    }

                    txtProductData!!.text = txtProductData!!.text.toString() + stringBuilder
                }
            }

            override fun onFailure(call: Call<ProductReviewsResponse>, t: Throwable) {
                txtProductData!!.text = t.message
            }
        })
    }
}
