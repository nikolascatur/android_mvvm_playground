package com.ruangguru.mvvmplaygroundarena.core.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<T : Any>(
    private val comparable: ComparableItem = ComparableItem(),
    @LayoutRes private val holderRes: Int,
    private val bind: (T, View) -> Unit,
    private val listener: (T, View, position: Int) -> Unit = { _, _, _ -> kotlin.run { } }
) : RecyclerView.Adapter<GenericAdapter.GenericViewHolder<T>>() {

    private val listData = mutableListOf<T>()

    fun setData(newData: List<T>) {
        calculateDiff(newData)
    }

    fun addData(newData: List<T>) {
        val list = ArrayList(this.listData)
        list.addAll(newData)
        calculateDiff(list)
    }

    fun clearData() {
        calculateDiff(emptyList())
    }

    private fun calculateDiff(newData: List<T>) {
        comparable.setList(listData, newData)
        val result = DiffUtil.calculateDiff(comparable)
        with(listData) {
            clear()
            addAll(newData)
        }
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val itemView = LayoutInflater.from(parent.context).inflate(holderRes, parent, false)
        return GenericViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bindView(listData[position], bind, listener)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class GenericViewHolder<T : Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: T, bind: (T, View) -> Unit, listener: (T, View, position: Int) -> Unit) {
            with(itemView) {
                bind.invoke(item, this)
                setOnClickListener {
                    listener.invoke(item, this, adapterPosition)
                }
            }

        }
    }
}