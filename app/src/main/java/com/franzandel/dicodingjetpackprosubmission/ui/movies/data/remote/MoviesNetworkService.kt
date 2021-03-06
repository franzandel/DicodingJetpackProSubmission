package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.remote

import com.franzandel.dicodingjetpackprosubmission.data.remote.NetworkService

/**
 * Created by Franz Andel on 28/02/21.
 * Android Engineer
 */

object MoviesNetworkService {
    fun getMoviesNetwork(): MoviesNetwork =
        NetworkService.getRetrofit().create(MoviesNetwork::class.java)
}