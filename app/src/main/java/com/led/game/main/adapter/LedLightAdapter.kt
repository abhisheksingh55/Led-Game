package com.led.game.main.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import android.view.ViewGroup
import com.led.domain.model.LedLight
import com.led.domain.model.LedModel
import com.led.game.R
import com.led.game.base.BaseDiffAdapter
import com.led.game.base.BaseViewHolder
import com.led.game.extensions.getColorRes
import com.led.game.extensions.inflate
import kotlinx.android.synthetic.main.item_led_light.view.*

class LedLightAdapter(context: Context) :
    BaseDiffAdapter<LedModel, BaseViewHolder<LedModel>>(null) {
    private val ledColors = context.resources.getIntArray(R.array.led_colors)
    override fun isItemSame(oldItem: LedModel, newItem: LedModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun isContentSame(oldItem: LedModel, newItem: LedModel): Boolean {
        return oldItem.light == newItem.light
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LedModel> {
        return LightVH(parent.inflate(R.layout.item_led_light))
    }

    inner class LightVH(view: View) : BaseViewHolder<LedModel>(view) {

        override fun setData(data: LedModel, payload: MutableList<Any>?) {
            super.setData(data, payload)
            itemView.colorIv.setColorFilter(
                ledColors[data.light.ordinal],
                PorterDuff.Mode.SRC_IN
            )
            itemView.nameTv.text = data.name
        }
    }
}