package com.quyenln.qmeal.data.model

import android.os.Parcelable
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
) : Parcelable
