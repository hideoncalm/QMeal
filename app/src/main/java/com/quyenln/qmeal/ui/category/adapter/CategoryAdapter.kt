package com.quyenln.qmeal.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.model.Category
import com.quyenln.qmeal.util.loadImageFromUrl
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categories = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false),
            listener
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.onBind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    fun updateData(newCategories: MutableList<Category>) {
        categories.clear()
        categories.addAll(newCategories)
        notifyDataSetChanged()
    }

    class CategoryViewHolder(
        itemView: View,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var category: Category

        init {
            itemView.setOnClickListener(this)
        }

        fun onBind(itemData: Category) {
            category = itemData.copy()
            itemView.apply {
                textCategory.text = itemData.name
                imageCategory.loadImageFromUrl(itemData.image)
            }
        }

        override fun onClick(v: View?) {
            listener.onItemClick(category)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(category: Category)
    }
}
