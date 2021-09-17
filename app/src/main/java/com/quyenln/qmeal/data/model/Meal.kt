package com.quyenln.qmeal.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meal(
    @SerializedName("idMeal")
    val id: Int,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val image: String
) : Parcelable {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem == newItem
            }

        }
    }
}
