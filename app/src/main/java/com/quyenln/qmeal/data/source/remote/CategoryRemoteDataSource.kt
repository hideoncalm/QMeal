package com.quyenln.qmeal.data.source.remote

import com.quyenln.qmeal.data.model.CategoryResponse
import com.quyenln.qmeal.data.source.CategoryDataSource
import com.quyenln.qmeal.data.source.remote.utils.APIService
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(
    private val apiService: APIService
) : CategoryDataSource {

    override suspend fun getCategories(): CategoryResponse = apiService.getCategories()
}
