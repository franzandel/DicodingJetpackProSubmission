package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.remote

import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShowsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Franz Andel on 06/03/21.
 * Android Engineer
 */

interface TvShowsNetwork {
    @GET("discover/tv")
    suspend fun getTvShowsFromAPI(@Query("api_key") apiKey: String): Response<TvShowsResponseDTO>
}