package com.quyenln.qmeal.data.source.remote.utils

import com.quyenln.qmeal.data.model.CategoryResponse
import com.quyenln.qmeal.data.model.IngredientResponse
import com.quyenln.qmeal.data.model.MealDetailResponse
import com.quyenln.qmeal.data.model.MealResponse
import com.quyenln.qmeal.data.source.remote.utils.APIConfig.BASE_CATEGORY
import com.quyenln.qmeal.data.source.remote.utils.APIConfig.BASE_FILTER
import com.quyenln.qmeal.data.source.remote.utils.APIConfig.BASE_INGREDIENT
import com.quyenln.qmeal.data.source.remote.utils.APIConfig.BASE_LOOKUP
import com.quyenln.qmeal.data.source.remote.utils.APIConfig.BASE_SEARCH
import com.quyenln.qmeal.data.source.remote.utils.APIConfig.PARAM_C
import com.quyenln.qmeal.data.source.remote.utils.APIConfig.PARAM_F
import com.quyenln.qmeal.data.source.remote.utils.APIConfig.PARAM_I
import com.quyenln.qmeal.data.source.remote.utils.APIConfig.PARAM_S
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    /**
     * Get all categories
     * https://www.themealdb.com/api/json/v1/1/categories.php
     */
    @GET(BASE_CATEGORY)
    suspend fun getCategories(): CategoryResponse

    /**
     * Get all ingerdients
     * https://www.themealdb.com/api/json/v1/1/list.php?i=list
     */
    @GET(BASE_INGREDIENT)
    suspend fun getIngredients(@Query(PARAM_I) ingredient: String = "list"): IngredientResponse

    /**
     * Get meals by category
     * https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
     */
    @GET(BASE_FILTER)
    suspend fun getMealsByCategory(@Query(PARAM_C) category: String): MealResponse

    /**
     * Get meals by ingredient
     * https://www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast
     */
    @GET(BASE_FILTER)
    suspend fun getMealsByIngredient(@Query(PARAM_I) ingredient: String): MealResponse?

    /**
     * Get meal detail by id
     * https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772
     */
    @GET(BASE_LOOKUP)
    suspend fun getMealById(@Query(PARAM_I) id: Int): MealDetailResponse

    /**
     * Search meals by first letter
     * https://www.themealdb.com/api/json/v1/1/search.php?f=a
     */
    @GET(BASE_SEARCH)
    suspend fun searchMealsByFirstLetter(@Query(PARAM_F) char: String)

    /**
     * Search meal by name
     * https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiatav
     */
    @GET(BASE_SEARCH)
    suspend fun searchMealByName(@Query(PARAM_S) name: String)

}
