package com.example.telstraassignment.ui.facts

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.telstraassignment.R
import com.example.telstraassignment.base.BaseViewModel
import com.example.telstraassignment.model.FactsResponseMain
import com.example.telstraassignment.network.FactsApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FactsListViewModel: BaseViewModel() {
    @Inject
    lateinit var factsApi: FactsApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val actionBarTitle:MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val factListAdapter: FactListAdapter = FactListAdapter()

    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    fun loadPosts(){
         subscription = factsApi.getFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveFactListStart() }
            .doOnTerminate { onRetrieveFactListFinish() }
            .subscribe(
                { result -> onRetrieveFactListSuccess(result) },
                { onRetrieveFactListError() }
            )
    }

    private fun onRetrieveFactListError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrieveFactListSuccess(factList:FactsResponseMain) {
        actionBarTitle.value = factList.title
        factListAdapter.updateFactList(factList = factList.rows)
    }

    private fun onRetrieveFactListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveFactListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}