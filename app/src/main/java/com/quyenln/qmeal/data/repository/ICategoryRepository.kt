package com.quyenln.qmeal.data.repository

import com.quyenln.qmeal.data.model.CategoryResponse

interface ICategoryRepository {

    suspend fun getCategories() : CategoryResponse
}
