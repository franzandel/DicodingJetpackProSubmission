package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.remote

import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MovieAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Franz Andel on 28/02/21.
 * Android Engineer
 */

interface MoviesNetwork {
    @GET("discover/movie")
    suspend fun getMoviesFromAPI(@Query("api_key") apiKey: String): Response<MovieAPI>
}