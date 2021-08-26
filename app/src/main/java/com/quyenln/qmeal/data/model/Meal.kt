package com.quyenln.qmeal.data.model

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idMeal")
    val id : Int,
    @SerializedName("strMeal")
    val name : String,
    @SerializedName("strMealThumb")
    val image : String
)
