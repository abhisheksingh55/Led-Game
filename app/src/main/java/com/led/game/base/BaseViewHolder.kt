package com.led.game.base

import android.view.View

abstract class BaseViewHolder<T>(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){

    var adapterCallback: BaseAdapterItemClick<T>?=null
    protected var dataModel: T?=null

    open fun setData(data: T, payload: MutableList<Any>?){
        dataModel = data
    }

    fun setCallbackListener(callback: BaseAdapterItemClick<T>?) {
        adapterCallback = callback
    }
}