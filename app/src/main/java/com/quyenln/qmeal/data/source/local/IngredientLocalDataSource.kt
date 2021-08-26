package com.quyenln.qmeal.data.source.local

import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.data.source.IngredientDataSource
import javax.inject.Inject

class IngredientLocalDataSource @Inject constructor(
    private val ingredientDao: MealDao
) : IngredientDataSource.Local {

    override suspend fun insertIngredient(ingredient: Ingredient) =
        ingredientDao.insertIngredient(ingredient)

    override suspend fun getLocalIngredients(): List<Ingredient>? =
        ingredientDao.getIngredients()

    override suspend fun deleteIngredient(ingredient: Ingredient) =
        ingredientDao.deleteIngredient(ingredient)

    override suspend fun isFavoriteIngredient(id: Int): Boolean =
        ingredientDao.isFavoriteIngredient(id)
}
