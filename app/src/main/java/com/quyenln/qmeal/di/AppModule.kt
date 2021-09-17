package com.quyenln.qmeal.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.quyenln.qmeal.data.source.local.AppDatabase
import com.quyenln.qmeal.data.source.remote.utils.APIConfig.BASE_URL
import com.quyenln.qmeal.data.source.remote.utils.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "qmeal.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideMealDao(db: AppDatabase) = db.getMealDao()

    @Singleton
    @Provides
    fun provideSharedPreference(
        @ApplicationContext app: Context
    ): SharedPreferences =
        app.getSharedPreferences("com.quyenln.qmeal.shared", MODE_PRIVATE)
}
