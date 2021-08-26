package com.quyenln.qmeal.data.source.local

import androidx.room.*
import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.data.model.MealDetail

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMeal(meal: MealDetail)

    @Query("select * from meal")
    suspend fun getMeals(): List<MealDetail>?

    @Delete
    suspend fun deleteMeal(meal: MealDetail)

    @Query("select exists (select 1 from meal where id = :id)")
    suspend fun isFavorite(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertIngredient(ingredient: Ingredient)

    @Query("select * from ingredient")
    suspend fun getIngredients(): List<Ingredient>?

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Query("select exists (select 1 from ingredient where id = :id)")
    suspend fun isFavoriteIngredient(id: Int): Boolean
}
