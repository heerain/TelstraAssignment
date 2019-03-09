package com.example.telstraassignment.ui.facts

import android.arch.lifecycle.MutableLiveData
import com.example.telstraassignment.base.BaseViewModel
import com.example.telstraassignment.model.FactsResponse


class FactViewModel:BaseViewModel() {
    private val factTitle = MutableLiveData<String>()
    private val factDescription = MutableLiveData<String>()
    private var factImageHref : String? = null

    fun bind(factResponse: FactsResponse){
        factTitle.value = factResponse.title
        factDescription.value = factResponse.description
        factImageHref = factResponse.imageHref
    }

    fun getFactTitle():MutableLiveData<String>{
        return factTitle
    }

    fun getFactDescription():MutableLiveData<String>{
        return factDescription
    }

    fun getImageUrl(): String? {
        return factImageHref
    }

}