package com.led.game.main.adapter

import android.graphics.PorterDuff
import android.view.View
import android.view.ViewGroup
import com.led.domain.model.LedLight
import com.led.game.R
import com.led.game.base.BaseAdapter
import com.led.game.base.BaseViewHolder
import com.led.game.extensions.getColorRes
import com.led.game.extensions.inflate
import kotlinx.android.synthetic.main.item_color_notation.view.*

class NotationAdapter: BaseAdapter<LedLight, BaseViewHolder<LedLight>>() {
    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LedLight> {
        return NotationVH(parent.inflate(R.layout.item_color_notation))
    }

    internal class NotationVH(view: View): BaseViewHolder<LedLight>(view){

        override fun setData(data: LedLight, payload: MutableList<Any>?) {
            super.setData(data, payload)
            var color: Int = -1
            var msg: String = ""
            when(data){
                LedLight.GREEN -> {
                    msg = "Button pressed was matched\nfor this position"
                    color = R.color.green
                }
                LedLight.RED -> {
                    msg = "Button pressed was wrong\nfor this sequence "
                    color = R.color.red
                }
                LedLight.ORANGE -> {
                    msg = "Button pressed was wrong\nfor this position"
                    color = R.color.orange
                }
                LedLight.OFF -> {
                    msg = "Button not pressed\nfor this position"
                    color = R.color.led_off
                }
            }
            itemView.msgTv.text = msg
            itemView.colorIv.setColorFilter(itemView.context.getColorRes(color), PorterDuff.Mode.SRC_IN)
        }
    }
}