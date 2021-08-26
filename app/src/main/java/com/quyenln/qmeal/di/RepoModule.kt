package com.quyenln.qmeal.di

import com.quyenln.qmeal.data.repository.ICategoryRepository
import com.quyenln.qmeal.data.repository.IIngredientRepository
import com.quyenln.qmeal.data.repository.IMealRepository
import com.quyenln.qmeal.data.repository.Impl.CategoryRepository
import com.quyenln.qmeal.data.repository.Impl.IngredientRepository
import com.quyenln.qmeal.data.repository.Impl.MealRepository
import com.quyenln.qmeal.data.source.CategoryDataSource
import com.quyenln.qmeal.data.source.IngredientDataSource
import com.quyenln.qmeal.data.source.MealDataSource
import com.quyenln.qmeal.data.source.local.IngredientLocalDataSource
import com.quyenln.qmeal.data.source.local.MealLocalDataSource
import com.quyenln.qmeal.data.source.remote.CategoryRemoteDataSource
import com.quyenln.qmeal.data.source.remote.IngredientRemoteDataSource
import com.quyenln.qmeal.data.source.remote.MealRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Singleton
    @Provides
    fun provideCategoryDataSource(categoryRemoteDataSource: CategoryRemoteDataSource): CategoryDataSource =
        categoryRemoteDataSource

    @Singleton
    @Provides
    fun provideCategoryRepository(categoryRepository: CategoryRepository): ICategoryRepository =
        categoryRepository

    @Singleton
    @Provides
    fun provideMealRemoteDataSource(mealRemoteDataSource: MealRemoteDataSource): MealDataSource.Remote =
        mealRemoteDataSource

    @Singleton
    @Provides
    fun provideMealLocalDataSource(mealLocalDataSource: MealLocalDataSource): MealDataSource.Local =
        mealLocalDataSource

    @Singleton
    @Provides
    fun provideMealRepository(mealRepository: MealRepository): IMealRepository = mealRepository

    @Singleton
    @Provides
    fun provideIngredientRemoteDataSource(ingredientRemoteDataSource: IngredientRemoteDataSource): IngredientDataSource.Remote =
        ingredientRemoteDataSource

    @Singleton
    @Provides
    fun provideIngredientLocalDataSource(ingredientLocalDataSource: IngredientLocalDataSource): IngredientDataSource.Local =
        ingredientLocalDataSource

    @Singleton
    @Provides
    fun provideIngredientRepository(ingredientRepository: IngredientRepository): IIngredientRepository =
        ingredientRepository
}
