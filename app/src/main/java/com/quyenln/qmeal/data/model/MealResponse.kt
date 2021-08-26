package com.quyenln.qmeal.data.model

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("meals")
    val meals : List<Meal>?
)
