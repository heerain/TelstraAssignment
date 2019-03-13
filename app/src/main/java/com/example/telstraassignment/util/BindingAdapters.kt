package com.example.telstraassignment.util

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.telstraassignment.util.extension.getParentActivity
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.example.telstraassignment.R
import com.squareup.picasso.Picasso


@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null) {
        Picasso.with(view.context)
            .load(imageUrl)
            .error(R.drawable.telstra_logo)
            .placeholder(R.drawable.telstra_logo)
            .into(view)
    }
}