package com.example.telstraassignment.ui.facts

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.example.telstraassignment.R
import com.example.telstraassignment.databinding.ActivityFactsListBinding

class FactsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFactsListBinding
    private lateinit var viewModel: FactsListViewModel
    private var errorSnackbar: Snackbar? = null
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_facts_list)
        binding.factsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(FactsListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        viewModel.actionBarTitle.observe(this, Observer { actiobarTitle ->
            if (actiobarTitle != null) setToolbarTitle(actiobarTitle)
        })

        binding.viewModel = viewModel

       configViews()
    }

    private fun configViews() {

        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh)
        mSwipeRefreshLayout.setOnRefreshListener {
            if(checkInternetConnectivity()) {
                viewModel.loadPosts()
            }else{
                showError(R.string.post_error)
            }
        }

    }

    private fun setToolbarTitle(title : String) {
        supportActionBar?.title = title
        if (mSwipeRefreshLayout.isRefreshing) {
            mSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    private fun checkInternetConnectivity() : Boolean{
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

}
