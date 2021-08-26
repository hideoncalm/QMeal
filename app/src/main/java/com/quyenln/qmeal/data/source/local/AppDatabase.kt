package com.quyenln.qmeal.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quyenln.qmeal.data.model.Ingredient
import com.quyenln.qmeal.data.model.MealDetail

@Database(
    version = 6,
    entities = [MealDetail::class, Ingredient::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMealDao(): MealDao
}
