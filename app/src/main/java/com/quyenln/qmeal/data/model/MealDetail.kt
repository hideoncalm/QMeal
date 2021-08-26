package com.quyenln.qmeal.data.model

import android.os.Parcelable
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
) : Parcelable
