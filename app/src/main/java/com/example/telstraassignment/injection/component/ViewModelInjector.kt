package com.example.telstraassignment.injection.component

import com.example.telstraassignment.injection.module.NetworkModule
import com.example.telstraassignment.ui.facts.FactViewModel
import com.example.telstraassignment.ui.facts.FactsListViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    fun inject(factsListViewModel: FactsListViewModel)
    fun inject(factsViewModel: FactViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}