package com.led.game.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.remove(){
    this.visibility = View.GONE
}

fun View.hide(){
    this.visibility = View.INVISIBLE
}

fun ViewGroup.inflate(resId: Int): View = LayoutInflater.from(this.context)
    .inflate(resId, this, false)