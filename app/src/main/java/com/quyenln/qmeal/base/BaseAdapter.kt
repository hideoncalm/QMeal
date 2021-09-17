package com.quyenln.qmeal.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<T, VH : BaseViewHolder<T>>(
    diffUtil: DiffUtil.ItemCallback<T>,
    private val listener: OnItemClickListener<T>
) : ListAdapter<T, VH>(diffUtil) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBindData(getItem(position))
    }

    fun updateData(data: List<T>) {
        submitList(data)
    }
}
