package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.remote

import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Franz Andel on 28/02/21.
 * Android Engineer
 */

object MoviesNetworkService {
    fun getMoviesNetwork(): MoviesNetwork {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConsts.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MoviesNetwork::class.java)
    }
}