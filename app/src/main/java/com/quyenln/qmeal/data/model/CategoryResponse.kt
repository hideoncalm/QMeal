package com.quyenln.qmeal.data.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categories")
    val categories : List<Category>
)
