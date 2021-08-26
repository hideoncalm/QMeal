package com.quyenln.qmeal.ui.ingredient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.Ingredient
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    private val ingredients = mutableListOf<Ingredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder =
        IngredientViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false),
            listener
        )

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.onBind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size

    fun updateData(newIngredients: MutableList<Ingredient>) {
        ingredients.clear()
        ingredients.addAll(newIngredients)
        notifyDataSetChanged()
    }

    class IngredientViewHolder(
        itemView: View,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var ingredient: Ingredient

        init {
            itemView.setOnClickListener(this)
        }

        fun onBind(itemData: Ingredient) {
            ingredient = itemData.copy()
            itemView.apply {
                textIngredient.text = itemData.name
                textTitle.text = itemData.title
            }
        }

        override fun onClick(v: View?) {
            listener.onItemClick(ingredient)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(ingredient: Ingredient)
    }
}
