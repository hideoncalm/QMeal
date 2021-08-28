package com.quyenln.qmeal.data.repository.Impl

import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.data.model.MealDetailResponse
import com.quyenln.qmeal.data.model.MealResponse
import com.quyenln.qmeal.data.repository.IMealRepository
import com.quyenln.qmeal.data.source.MealDataSource
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val remote: MealDataSource.Remote,
    private val local: MealDataSource.Local
) : IMealRepository {
    override suspend fun getMealsByCategory(category: String): MealResponse =
        remote.getMealsByCategory(category)

    override suspend fun getMealById(id: Int): MealDetailResponse =
        remote.getMealById(id)

    override suspend fun searchMealByName(name: String): MealDetailResponse? =
        remote.searchMealByName(name)

    override suspend fun searchMealsByFirstLetter(s: Char): MealDetailResponse? =
        remote.searchMealsByFirstLetter(s)

    override suspend fun getSingleRandomMeal(): MealDetailResponse =
        remote.getSingleRandomMeal()

    override suspend fun insertMeal(meal: MealDetail) =
        local.insertMeal(meal)

    override suspend fun getMeals(): List<MealDetail>? =
        local.getMeals()

    override suspend fun deleteMeal(meal: MealDetail) =
        local.deleteMeal(meal)

    override suspend fun isFavorite(id: Int): Boolean =
        local.isFavorite(id)
}
