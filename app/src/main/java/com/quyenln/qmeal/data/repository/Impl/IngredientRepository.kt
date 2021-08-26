package com.quyenln.qmeal.data.repository.Impl

import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.data.model.IngredientResponse
import com.quyenln.qmeal.data.model.MealResponse
import com.quyenln.qmeal.data.repository.IIngredientRepository
import com.quyenln.qmeal.data.source.IngredientDataSource
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val ingredientRemoteDataSource: IngredientDataSource.Remote,
    private val ingredientLocalDataSource: IngredientDataSource.Local
) : IIngredientRepository {

    override suspend fun getIngredients(): IngredientResponse =
        ingredientRemoteDataSource.getIngredients()

    override suspend fun getMealsByIngredient(id: String): MealResponse? =
        ingredientRemoteDataSource.getMealsByIngredient(id)

    override suspend fun insertIngredient(ingredient: Ingredient) =
        ingredientLocalDataSource.insertIngredient(ingredient)

    override suspend fun getLocalIngredients(): List<Ingredient>? =
        ingredientLocalDataSource.getLocalIngredients()

    override suspend fun deleteIngredient(ingredient: Ingredient) =
        ingredientLocalDataSource.deleteIngredient(ingredient)

    override suspend fun isFavoriteIngredient(id: Int): Boolean =
        ingredientLocalDataSource.isFavoriteIngredient(id)
}
