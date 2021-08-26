package com.quyenln.qmeal.data.source

import com.quyenln.qmeal.data.model.CategoryResponse

interface CategoryDataSource {
    suspend fun getCategories() : CategoryResponse
}
