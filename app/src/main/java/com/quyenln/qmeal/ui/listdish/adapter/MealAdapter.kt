package com.quyenln.qmeal.ui.listdish.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quyenln.qmeal.R
import com.quyenln.qmeal.base.BaseAdapter
import com.quyenln.qmeal.base.BaseViewHolder
import com.quyenln.qmeal.base.OnItemClickListener
import com.quyenln.qmeal.data.model.Meal
import com.quyenln.qmeal.util.loadImageFromUrl
import kotlinx.android.synthetic.main.item_dish.view.*

class MealAdapter(
    private val listener: OnItemClickListener<Meal>
) : BaseAdapter<Meal, MealAdapter.MealHolder>(Meal.diffUtil, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MealHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false), listener
    )

    class MealHolder(
        itemView: View,
        listener: OnItemClickListener<Meal>
    ) : BaseViewHolder<Meal>(itemView, listener) {

        override fun onBindData(itemData: Meal) {
            super.onBindData(itemData)
            itemView.apply {
                textDish.text = itemData.name
                imageDish.loadImageFromUrl(itemData.image)
            }
        }

    }
}
