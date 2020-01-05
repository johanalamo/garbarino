package com.example.johan.products

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout

class ProductListActivity : AppCompatActivity(), ProductListFragment.OnFragmentInteractionListener {

    private val TAG = ProductListActivity::class.java.simpleName

    private var productListFragment:ProductListFragment? = null

    private var productDetailsFragment: ProductDetailsFragment? = null

    private var fragmentContainer: FrameLayout? = null

    private var isLargeScreen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main_activity)

        fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container)

        isLargeScreen = (fragmentContainer != null)

        var cols: Int = if (isLargeScreen) 1  else 2

        productListFragment = ProductListFragment.newInstance(cols)
        productListFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, it)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onListItemClicked(productId:String) { //element: Product) {
        if (!isLargeScreen) {
            val intent: Intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("p_product_id", productId)
            this.startActivity(intent)
        } else {

            productDetailsFragment = ProductDetailsFragment.newInstance(productId)
            productDetailsFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .addToBackStack(null)
                    .commit()

            }

        }
    }
}
