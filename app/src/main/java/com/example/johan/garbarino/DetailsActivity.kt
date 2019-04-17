package com.example.johan.garbarino

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
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
//        this.productId = getIntent().getExtras().getString("p_product_id")      
        this.productId = "0982a08485"
        txtMessage.text = "ProductId: " + this.productId + "\n\n" + " Módulo en construcción"

        txtProductData = findViewById(R.id.textView)
        findViewById<View>(R.id.button).setOnClickListener { getCurrentData() }

    }

    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductDataService::class.java)
        val call = service.getProductData(lat, lon, AppId)
        call.enqueue(object : Callback<ProductDataResponse> {
            override fun onResponse(call: Call<ProductDataResponse>, response: Response<ProductDataResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val stringBuilder = "Country: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main!!.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main!!.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main!!.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main!!.pressure

                    txtProductData!!.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<ProductDataResponse>, t: Throwable) {
                txtProductData!!.text = t.message
            }
        })
    }
    companion object {

        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "2e65127e909e178d0af311a81f39948c"
        var lat = "35"
        var lon = "139"
    }
}
