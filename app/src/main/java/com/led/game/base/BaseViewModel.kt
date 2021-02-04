package com.led.game.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * this is application base viewmodel, extend this class for creating any ViewModel
 */
open class BaseViewModel @Inject constructor(): ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }

}