package com.example.telstraassignment.util

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.telstraassignment.util.extension.getParentActivity
import android.arch.lifecycle.Observer



@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}