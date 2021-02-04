package com.led.game.base

interface BaseAdapterItemClick<T> {

    fun onItemClick(viewType: Int, data:T){

    }

    fun onItemClick(viewType: Int, data:T, position:Int)
    {

    }
}