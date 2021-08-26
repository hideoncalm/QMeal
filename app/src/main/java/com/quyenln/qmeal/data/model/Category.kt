package com.quyenln.qmeal.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory")
    val id: Int,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryThumb")
    val image: String,
    @SerializedName("strCategoryDescription")
    val title: String
)
