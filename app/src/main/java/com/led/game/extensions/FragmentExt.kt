package com.led.game.extensions

import androidx.fragment.app.Fragment

fun Fragment.isViewLive(): Boolean{
    return this.isAdded && this.view != null
}