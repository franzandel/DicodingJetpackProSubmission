package com.franzandel.dicodingjetpackprosubmission.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.dao.FavoriteMovieDao
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieDTO

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@Database(
    entities = [FavoriteMovieDTO::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}