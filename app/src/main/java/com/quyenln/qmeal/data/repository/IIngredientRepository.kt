package com.quyenln.qmeal.data.repository

import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.data.model.IngredientResponse
import com.quyenln.qmeal.data.model.MealResponse

interface IIngredientRepository {
    suspend fun getIngredients(): IngredientResponse
    suspend fun getMealsByIngredient(id: String): MealResponse?

    suspend fun insertIngredient(ingredient: Ingredient)
    suspend fun getLocalIngredients(): List<Ingredient>?
    suspend fun deleteIngredient(ingredient: Ingredient)
    suspend fun isFavoriteIngredient(id: Int): Boolean
}
