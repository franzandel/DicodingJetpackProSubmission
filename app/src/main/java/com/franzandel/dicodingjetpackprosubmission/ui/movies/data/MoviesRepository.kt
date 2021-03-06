package com.franzandel.dicodingjetpackprosubmission.ui.movies.data

import androidx.lifecycle.LiveData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MovieAPI

/**
 * Created by Franz Andel on 28/02/21.
 * Android Engineer
 */

interface MoviesRepository {
    suspend fun getMovies(): LiveData<Resource<MovieAPI>>
}