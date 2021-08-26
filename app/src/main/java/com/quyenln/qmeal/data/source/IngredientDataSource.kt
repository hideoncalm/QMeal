package com.quyenln.qmeal.data.source

import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.data.model.IngredientResponse
import com.quyenln.qmeal.data.model.MealResponse

interface IngredientDataSource {
    interface Remote {
        suspend fun getIngredients(): IngredientResponse
        suspend fun getMealsByIngredient(id: String): MealResponse?
    }

    interface Local {
        suspend fun insertIngredient(ingredient: Ingredient)
        suspend fun getLocalIngredients(): List<Ingredient>?
        suspend fun deleteIngredient(ingredient: Ingredient)
        suspend fun isFavoriteIngredient(id: Int): Boolean
    }
}
