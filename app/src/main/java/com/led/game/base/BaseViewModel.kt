package com.led.game.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * this is application base viewmodel, extend this class for creating any ViewModel
 */
open class BaseViewModel @Inject constructor(): ViewModel() {

    protected val compositeDisposable = CompositeDisposable()
    private val toastMsgLv = MutableLiveData<String>()

    fun observeMsg(): LiveData<String> {
        return toastMsgLv
    }

    fun postToastMsg(msg: String){
        toastMsgLv.postValue(msg)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

}