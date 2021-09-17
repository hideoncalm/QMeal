package com.quyenln.qmeal.ui.favorite.ingredient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quyenln.qmeal.R
import com.quyenln.qmeal.base.BaseAdapter
import com.quyenln.qmeal.base.BaseViewHolder
import com.quyenln.qmeal.base.OnItemButtonClickListener
import com.quyenln.qmeal.base.OnItemClickListener
import com.quyenln.qmeal.data.model.Ingredient
import kotlinx.android.synthetic.main.item_ingredient_favorite.view.*


class FavoriteIngredientAdapter(
    private val listener: OnItemClickListener<Ingredient>,
    private val heartListener: OnItemButtonClickListener<Ingredient>
) : BaseAdapter<Ingredient, FavoriteIngredientAdapter.IngredientMealHolder>(
    Ingredient.diffUtil,
    listener
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IngredientMealHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient_favorite, parent, false),
        listener,
        heartListener
    )

    class IngredientMealHolder(
        itemView: View,
        listener: OnItemClickListener<Ingredient>,
        heartListener: OnItemButtonClickListener<Ingredient>
    ) : BaseViewHolder<Ingredient>(itemView, listener) {

        init {
            itemView.buttonUnFavorite.setOnClickListener {
                item?.let { it1 -> heartListener.onButtonHeartClick(it1) }
            }
        }

        override fun onBindData(itemData: Ingredient) {
            super.onBindData(itemData)
            itemView.apply {
                textIngredient.text = itemData.name
                textTitle.text = itemData.title
            }
        }
    }
}
