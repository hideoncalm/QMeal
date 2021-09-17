package com.quyenln.qmeal.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quyenln.qmeal.R
import com.quyenln.qmeal.base.BaseAdapter
import com.quyenln.qmeal.base.BaseViewHolder
import com.quyenln.qmeal.base.OnItemClickListener
import com.quyenln.qmeal.data.model.Category
import com.quyenln.qmeal.util.loadImageFromUrl
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(
    private val listener: OnItemClickListener<Category>
) : BaseAdapter<Category, CategoryAdapter.CategoryViewHolder>(Category.diffUtil, listener) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder = CategoryViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_category,
            parent,
            false
        ), listener
    )

    class CategoryViewHolder(
        itemView: View,
        listener: OnItemClickListener<Category>
    ) : BaseViewHolder<Category>(itemView, listener) {

        override fun onBindData(itemData: Category) {
            super.onBindData(itemData)
            itemView.apply {
                textCategory.text = itemData.name
                imageCategory.loadImageFromUrl(itemData.image)
            }
        }
    }
}
