package com.quyenln.qmeal.data.source.remote

import com.quyenln.qmeal.data.model.MealDetailResponse
import com.quyenln.qmeal.data.source.MealDataSource
import com.quyenln.qmeal.data.source.remote.utils.APIService
import javax.inject.Inject

class MealRemoteDataSource @Inject constructor(
    private val apiService: APIService
) : MealDataSource.Remote {

    override suspend fun getMealsByCategory(category: String) =
        apiService.getMealsByCategory(category)

    override suspend fun getMealById(id: Int): MealDetailResponse =
        apiService.getMealById(id)

    override suspend fun searchMealsByFirstLetter(s: Char): MealDetailResponse? =
        apiService.searchMealsByFirstLetter(s)

    override suspend fun searchMealByName(name: String): MealDetailResponse? =
        apiService.searchMealByName(name)

    override suspend fun getSingleRandomMeal(): MealDetailResponse =
        apiService.getSingleRandomMeal()

}
