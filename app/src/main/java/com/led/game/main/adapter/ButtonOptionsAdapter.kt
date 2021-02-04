package com.led.game.main.adapter

import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.led.domain.model.ButtonOption
import com.led.game.R
import com.led.game.base.BaseAdapter
import com.led.game.base.BaseAdapterItemClick
import com.led.game.base.BaseViewHolder
import com.led.game.extensions.inflate
import kotlinx.android.synthetic.main.item_button_option.view.*
import java.util.concurrent.TimeUnit

class ButtonOptionsAdapter(callBack: BaseAdapterItemClick<ButtonOption>): BaseAdapter<ButtonOption, BaseViewHolder<ButtonOption>>(callBack) {
    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ButtonOption> {
        return ButtonVH(parent.inflate(R.layout.item_button_option))
    }

    inner class ButtonVH(view: View): BaseViewHolder<ButtonOption>(view){

        init {

            RxView.clicks(itemView.optionBtv)
                .filter { dataModel != null }
                .subscribe({
                    adapterCallback?.onItemClick(-1, dataModel!!)
                },{

                }).let {  }
        }
        override fun setData(data: ButtonOption, payload: MutableList<Any>?) {
            super.setData(data, payload)
            itemView.optionBtv.text = "Button ".plus(data.name)
        }
    }
}