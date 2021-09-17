package com.quyenln.qmeal.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "meal")
@Parcelize
data class MealDetail(
    @SerializedName("idMeal")
    @PrimaryKey
    val id: Int,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strInstructions")
    val title: String,
    @SerializedName("strMealThumb")
    val image: String,
    @SerializedName("strYoutube")
    var youtubeLink: String,
    val ingredients: String? = ""
) : Parcelable {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MealDetail>() {
            override fun areItemsTheSame(oldItem: MealDetail, newItem: MealDetail): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MealDetail, newItem: MealDetail): Boolean {
                return oldItem == newItem
            }

        }
    }
}
