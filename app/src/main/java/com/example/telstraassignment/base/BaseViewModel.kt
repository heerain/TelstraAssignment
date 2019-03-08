package com.example.telstraassignment.base


import android.arch.lifecycle.ViewModel
import com.example.telstraassignment.injection.component.DaggerViewModelInjector
import com.example.telstraassignment.injection.component.ViewModelInjector
import com.example.telstraassignment.injection.module.NetworkModule
import com.example.telstraassignment.ui.facts.FactsListViewModel


abstract class BaseViewModel : ViewModel(){

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is FactsListViewModel -> injector.injet(this)
        }
    }

}
