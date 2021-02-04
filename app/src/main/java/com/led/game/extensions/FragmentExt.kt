package com.led.game.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun Fragment.isViewLive(): Boolean{
    return this.isAdded && this.view != null
}

inline fun <reified T : ViewModel> ViewModelProvider.Factory.getViewModel(activity: FragmentActivity): T {
    return ViewModelProvider(activity, this)[T::class.java]
}

inline fun <reified T : ViewModel> ViewModelProvider.Factory.getViewModel(fragment: Fragment): T {
    return ViewModelProvider(fragment, this)[T::class.java]
}