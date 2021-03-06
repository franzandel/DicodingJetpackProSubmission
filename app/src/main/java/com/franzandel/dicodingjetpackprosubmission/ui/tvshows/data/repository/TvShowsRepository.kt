package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository

import androidx.lifecycle.LiveData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShowsAPI

/**
 * Created by Franz Andel on 06/03/21.
 * Android Engineer
 */

interface TvShowsRepository {
    suspend fun getTvShows(): LiveData<Resource<TvShowsAPI>>
}