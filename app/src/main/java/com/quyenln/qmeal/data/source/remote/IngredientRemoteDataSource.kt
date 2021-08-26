package com.quyenln.qmeal.data.source.remote

import com.quyenln.qmeal.data.model.IngredientResponse
import com.quyenln.qmeal.data.model.MealResponse
import com.quyenln.qmeal.data.source.IngredientDataSource
import com.quyenln.qmeal.data.source.remote.utils.APIService
import javax.inject.Inject

class IngredientRemoteDataSource @Inject constructor(
    private val apiService: APIService
) : IngredientDataSource.Remote {

    override suspend fun getIngredients(): IngredientResponse =
        apiService.getIngredients()

    override suspend fun getMealsByIngredient(id: String): MealResponse? =
        apiService.getMealsByIngredient(id)
}
