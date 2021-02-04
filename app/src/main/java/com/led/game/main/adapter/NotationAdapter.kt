package com.led.game.main.adapter

import android.content.Context
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

class NotationAdapter(context: Context) : BaseAdapter<LedLight, BaseViewHolder<LedLight>>() {

    private val colorNotations = context.resources.getStringArray(R.array.color_notation)
    private val ledColors = context.resources.getIntArray(R.array.led_colors)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LedLight> {
        return NotationVH(parent.inflate(R.layout.item_color_notation))
    }

    inner class NotationVH(view: View) : BaseViewHolder<LedLight>(view) {

        override fun setData(data: LedLight, payload: MutableList<Any>?) {
            super.setData(data, payload)
            itemView.msgTv.text = colorNotations[data.ordinal]
            itemView.colorIv.setColorFilter(
                ledColors[data.ordinal],
                PorterDuff.Mode.SRC_IN
            )
        }
    }
}