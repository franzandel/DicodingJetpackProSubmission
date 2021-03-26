package com.franzandel.dicodingjetpackprosubmission.di

import android.content.Context
import androidx.room.Room
import com.franzandel.dicodingjetpackprosubmission.data.consts.ApiConsts
import com.franzandel.dicodingjetpackprosubmission.data.local.BookmarkDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Franz Andel on 13/03/21.
 * Android Engineer
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BOOKMARK_DB_NAME = "BookmarkDatabase.db"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConsts.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideBookmarkDatabase(@ApplicationContext context: Context): BookmarkDatabase =
        Room.databaseBuilder(
            context,
            BookmarkDatabase::class.java,
            BOOKMARK_DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
}