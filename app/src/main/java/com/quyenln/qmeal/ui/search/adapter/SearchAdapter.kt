package com.quyenln.qmeal.ui.listdish.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.Meal
import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.util.loadImageFromUrl
import kotlinx.android.synthetic.main.item_dish.view.*

class SearchAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<SearchAdapter.MealViewHolder>() {

    private val meals = mutableListOf<MealDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder =
        MealViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false),
            listener
        )

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.onBind(meals[position])
    }

    override fun getItemCount(): Int = meals.size

    fun updateData(newMeals: MutableList<MealDetail>) {
        meals.clear()
        meals.addAll(newMeals)
        notifyDataSetChanged()
    }

    class MealViewHolder(
        itemView: View,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var meal: MealDetail

        init {
            itemView.setOnClickListener(this)
        }

        fun onBind(itemData: MealDetail) {
            meal = itemData.copy()
            itemView.apply {
                textDish.text = itemData.name
                imageDish.loadImageFromUrl(itemData.image)
            }
        }

        override fun onClick(v: View?) {
            listener.onItemClick(meal)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(meal: MealDetail)
    }
}
