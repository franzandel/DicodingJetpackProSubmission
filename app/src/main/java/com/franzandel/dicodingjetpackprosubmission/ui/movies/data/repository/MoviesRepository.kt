package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository

import androidx.lifecycle.LiveData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MoviesResponseDTO

/**
 * Created by Franz Andel on 28/02/21.
 * Android Engineer
 */

interface MoviesRepository {
    suspend fun getMovies(): LiveData<Resource<MoviesResponseDTO>>
}