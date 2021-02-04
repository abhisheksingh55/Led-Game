package com.led.game.base

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffAdapter<M, T : BaseViewHolder<M>>(callBack: BaseAdapterItemClick<M>?) :
    BaseAdapter<M, T>(callBack) {

    fun updateDiffList(list: List<M>){
        val diff = DiffUtil.calculateDiff(DiffUtils(list))
        diff.dispatchUpdatesTo(this)
        addItems(list)
    }

    abstract fun isItemSame(oldItem: M, newItem: M): Boolean

    abstract fun isContentSame(oldItem: M, newItem: M): Boolean

    inner class DiffUtils(private val newList: List<M>): DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return isItemSame(itemList[oldItemPosition], newList[newItemPosition])
        }

        override fun getOldListSize(): Int {
            return itemList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return isContentSame(itemList[oldItemPosition], newList[newItemPosition])
        }
    }
}