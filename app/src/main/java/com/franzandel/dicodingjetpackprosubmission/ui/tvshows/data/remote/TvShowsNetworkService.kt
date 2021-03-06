package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.remote

import com.franzandel.dicodingjetpackprosubmission.data.remote.NetworkService

/**
 * Created by Franz Andel on 06/03/21.
 * Android Engineer
 */

object TvShowsNetworkService {
    fun getTvShowsNetwork(): TvShowsNetwork =
        NetworkService.getRetrofit().create(TvShowsNetwork::class.java)
}