package com.quyenln.qmeal.data.repository

import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.data.model.MealDetailResponse
import com.quyenln.qmeal.data.model.MealResponse

interface IMealRepository {
    suspend fun getMealsByCategory(category: String): MealResponse
    suspend fun getMealById(id: Int): MealDetailResponse
    suspend fun searchMealByName(name: String): MealDetailResponse?
    suspend fun searchMealsByFirstLetter(s: Char): MealDetailResponse?
    suspend fun getSingleRandomMeal(): MealDetailResponse

    suspend fun insertMeal(meal: MealDetail)
    suspend fun getMeals(): List<MealDetail>?
    suspend fun deleteMeal(meal: MealDetail)
    suspend fun isFavorite(id: Int): Boolean
}
