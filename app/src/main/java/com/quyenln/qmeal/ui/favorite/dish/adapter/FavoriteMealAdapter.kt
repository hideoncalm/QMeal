package com.quyenln.qmeal.ui.favorite.dish.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quyenln.qmeal.R
import com.quyenln.qmeal.base.BaseAdapter
import com.quyenln.qmeal.base.BaseViewHolder
import com.quyenln.qmeal.base.OnItemButtonClickListener
import com.quyenln.qmeal.base.OnItemClickListener
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.util.loadImageFromUrl
import kotlinx.android.synthetic.main.item_dish_favorite.view.*

class FavoriteMealAdapter(
    private val listener: OnItemClickListener<MealDetail>,
    private val heartListener: OnItemButtonClickListener<MealDetail>
) : BaseAdapter<MealDetail, FavoriteMealAdapter.FavoriteMealHolder>(
    MealDetail.diffUtil,
    listener
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoriteMealHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_dish_favorite, parent, false),
        listener,
        heartListener
    )

    class FavoriteMealHolder(
        itemView: View,
        listener: OnItemClickListener<MealDetail>,
        heartListener: OnItemButtonClickListener<MealDetail>
    ) : BaseViewHolder<MealDetail>(itemView, listener) {

        init {
            itemView.buttonUnFavorite.setOnClickListener {
                item?.let { it1 -> heartListener.onButtonHeartClick(it1) }
            }
        }

        override fun onBindData(itemData: MealDetail) {
            super.onBindData(itemData)
            itemView.apply {
                textDish.text = itemData.name
                imageDish.loadImageFromUrl(itemData.image)
            }
        }
    }
}
