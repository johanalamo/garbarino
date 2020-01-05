package com.example.johan.products

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.johan.products.adapter.ProductListRecyclerViewAdapter
import com.example.johan.products.response.Product
import com.example.johan.products.viewmodel.ProductListViewModel


private const val ARG_COLS = "cols"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProductListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductListFragment : Fragment(), ProductListRecyclerViewAdapter.ClickListener {
    // TODO: Rename and change types of parameters
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var viewModel: ProductListViewModel

    private lateinit var recyclerView: RecyclerView

    private var cols: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cols = it.getString(ARG_COLS, "1").toInt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    override fun listItemClicked(productId: String) {
        listener?.onListItemClicked(productId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
        viewModel.getProductList().observe(this,
            Observer { productList ->
                createRecyclerViewProductList(productList!!.items)
            }
        )
        viewModel.loadProductListData()
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun createRecyclerViewProductList(data: Array<Product>) {
        view?.let {
            recyclerView = it.findViewById<RecyclerView>(R.id.rviewProducts)
            recyclerView.setHasFixedSize(false)
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(cols, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.adapter = ProductListRecyclerViewAdapter(data, context as AppCompatActivity, this)
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListItemClicked(productId: String)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(cols: Int = 2) = ProductListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_COLS, cols.toString())
            }
        }
    }
}
