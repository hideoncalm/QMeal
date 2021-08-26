package com.quyenln.qmeal.data.repository.Impl

import com.quyenln.qmeal.data.model.CategoryResponse
import com.quyenln.qmeal.data.repository.ICategoryRepository
import com.quyenln.qmeal.data.source.CategoryDataSource
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDataSource: CategoryDataSource
) : ICategoryRepository {
    override suspend fun getCategories(): CategoryResponse = categoryDataSource.getCategories()
}
