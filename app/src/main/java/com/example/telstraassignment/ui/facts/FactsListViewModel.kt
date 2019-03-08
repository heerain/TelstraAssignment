package com.example.telstraassignment.ui.facts

import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.widget.Toast
import com.example.telstraassignment.R
import com.example.telstraassignment.base.BaseViewModel
import com.example.telstraassignment.network.FactsApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FactsListViewModel: BaseViewModel() {
    @Inject
    lateinit var factsApi: FactsApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = factsApi.getFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveFactListStart() }
            .doOnTerminate { onRetrieveFactListFinish() }
            .subscribe(
                { onRetrieveFactListSuccess() },
                { onRetrieveFactListError() }
            )
    }

    private fun onRetrieveFactListError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrieveFactListSuccess() {
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