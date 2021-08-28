package com.quyenln.qmeal.data.source

import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.data.model.MealDetailResponse
import com.quyenln.qmeal.data.model.MealResponse

interface MealDataSource {
    interface Remote {
        suspend fun getMealsByCategory(category: String): MealResponse
        suspend fun getMealById(id: Int): MealDetailResponse
        suspend fun searchMealsByFirstLetter(s : Char) : MealDetailResponse?
        suspend fun searchMealByName(name : String) : MealDetailResponse?
        suspend fun getSingleRandomMeal() : MealDetailResponse
    }

    interface Local {
        suspend fun insertMeal(meal: MealDetail)
        suspend fun getMeals(): List<MealDetail>?
        suspend fun deleteMeal(meal: MealDetail)
        suspend fun isFavorite(id: Int): Boolean
    }
}
