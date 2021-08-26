package com.quyenln.qmeal.data.source.local

import com.quyenln.qmeal.data.model.MealDetail
import com.quyenln.qmeal.data.source.MealDataSource
import javax.inject.Inject

class MealLocalDataSource @Inject constructor(
    private val mealDao: MealDao
) : MealDataSource.Local {

    override suspend fun insertMeal(meal: MealDetail) =
        mealDao.insertMeal(meal)

    override suspend fun getMeals(): List<MealDetail>? =
        mealDao.getMeals()

    override suspend fun deleteMeal(meal: MealDetail) =
        mealDao.deleteMeal(meal)

    override suspend fun isFavorite(id: Int): Boolean =
        mealDao.isFavorite(id)
}
