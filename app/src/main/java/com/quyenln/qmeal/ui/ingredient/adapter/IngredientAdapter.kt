package com.quyenln.qmeal.ui.ingredient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quyenln.qmeal.R
import com.quyenln.qmeal.base.BaseAdapter
import com.quyenln.qmeal.base.BaseViewHolder
import com.quyenln.qmeal.base.OnItemClickListener
import com.quyenln.qmeal.data.model.Ingredient
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientAdapter(
    private val listener: OnItemClickListener<Ingredient>
) : BaseAdapter<Ingredient, IngredientAdapter.IngredientViewHolder>(
    Ingredient.diffUtil,
    listener
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = IngredientViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false),
        listener
    )

    class IngredientViewHolder(
        itemView: View,
        listener: OnItemClickListener<Ingredient>
    ) : BaseViewHolder<Ingredient>(itemView, listener) {

        override fun onBindData(itemData: Ingredient) {
            super.onBindData(itemData)
            itemView.apply {
                textIngredient.text = itemData.name
                textTitle.text = itemData.title
            }
        }
    }
}
