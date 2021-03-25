package com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieDTO

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM tbl_favorite_movie")
    fun getFavoriteMovies(): LiveData<List<FavoriteMovieDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovieDTO: FavoriteMovieDTO): Long

    @Query("DELETE FROM tbl_favorite_movie WHERE id = :id")
    suspend fun deleteFavoriteMovie(id: Int): Int
}