package com.quyenln.qmeal.data.model

import com.google.gson.annotations.SerializedName

data class MealDetailResponse(
    @SerializedName("meals")
    val meals: List<MealDetail>?
)
