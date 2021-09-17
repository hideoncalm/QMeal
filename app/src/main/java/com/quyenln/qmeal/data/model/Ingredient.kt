package com.quyenln.qmeal.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "ingredient")
@Parcelize
data class Ingredient(
    @SerializedName("idIngredient")
    @PrimaryKey
    val id: Int,
    @SerializedName("strIngredient")
    val name: String,
    @SerializedName("strDescription")
    val title: String?
) : Parcelable {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Ingredient>() {
            override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
                return oldItem == newItem
            }

        }
    }
}
