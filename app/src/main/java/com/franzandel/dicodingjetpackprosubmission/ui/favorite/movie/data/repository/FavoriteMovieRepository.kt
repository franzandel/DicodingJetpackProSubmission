package com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.repository

import androidx.lifecycle.LiveData
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieResponse

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

interface FavoriteMovieRepository {
    suspend fun add(favoriteMovieRequest: FavoriteMovieRequest): Long
    suspend fun delete(id: Int): Int
    suspend fun getAll(): LiveData<List<FavoriteMovieResponse>>
}