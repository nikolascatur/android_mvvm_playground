package com.ruangguru.mvvmplaygroundarena.core.common

import androidx.recyclerview.widget.DiffUtil

class ComparableItem: DiffUtil.Callback() {
    private var oldList: List<Any> = emptyList()
    private var newList: List<Any> = emptyList()
    fun setList(oldList: List<Any>, newList: List<Any>) {
        this.oldList = oldList
        this.newList = newList
    }
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}