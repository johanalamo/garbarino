package com.example.johan.products

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ProductListActivity : AppCompatActivity(), ProductListFragment.OnFragmentInteractionListener {

    private var productListFragment:ProductListFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main_activity)

        productListFragment = ProductListFragment.newInstance()
        productListFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, it)
                .addToBackStack(null)
                .commit()
        }


    }



    override fun onListItemClicked(productId:String) { //element: Product) {
        val intent: Intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("p_product_id", productId)
        this.startActivity(intent)
    }
}
