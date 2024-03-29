package com.quyenln.qmeal.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quyenln.qmeal.R
import com.quyenln.qmeal.base.BaseAdapter
import com.quyenln.qmeal.base.BaseViewHolder
import com.quyenln.qmeal.base.OnItemClickListener
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.util.loadImageFromUrl
import kotlinx.android.synthetic.main.item_dish.view.*

class SearchAdapter(
    private val listener: OnItemClickListener<MealDetail>
) : BaseAdapter<MealDetail, SearchAdapter.SearchHolder>(MealDetail.diffUtil, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false), listener
    )

    class SearchHolder(
        itemView: View,
        listener: OnItemClickListener<MealDetail>
    ) : BaseViewHolder<MealDetail>(itemView, listener) {

        override fun onBindData(itemData: MealDetail) {
            super.onBindData(itemData)
            itemView.apply {
                textDish.text = itemData.name
                imageDish.loadImageFromUrl(itemData.image)
            }
        }
    }
}
