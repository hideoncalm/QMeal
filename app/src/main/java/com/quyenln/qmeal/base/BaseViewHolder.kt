package com.quyenln.qmeal.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    itemView: View,
    private val listener: OnItemClickListener<T>
) : RecyclerView.ViewHolder(itemView) {

    var item: T? = null

    init {
        itemView.setOnClickListener {
            item?.let { item -> listener.onItemClick(item) }
        }
    }

    open fun onBindData(itemData: T) {
        this.item = itemData
    }
}
