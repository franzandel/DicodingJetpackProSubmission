package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseNetworkRepository
import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import com.franzandel.dicodingjetpackprosubmission.external.ErrorCodeData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.TmdbErrorResponse
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShowsAPI
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.remote.TvShowsNetwork
import com.google.gson.Gson

class TvShowsRepositoryImpl(private val network: TvShowsNetwork, gson: Gson) :
    BaseNetworkRepository(gson), TvShowsRepository {

    override suspend fun getTvShows(): LiveData<Resource<TvShowsAPI>> {
        val tvShowsResponse = MutableLiveData<Resource<TvShowsAPI>>()

        return withTryCatch(tvShowsResponse) {
            network.getTvShowsFromAPI(AppConsts.apiKey).apply {
                if (isSuccessful) {
                    tvShowsResponse.postValue(Resource.success(body()))
                } else {
                    val tvShowsErrorResponse =
                        getParsedErrorModel(errorBody(), TmdbErrorResponse::class.java)
                    val errorMessage = tvShowsErrorResponse.status_message
                    val errorCodeData = ErrorCodeData(code(), errorMessage)
                    tvShowsResponse.postValue(Resource.error(null, errorCodeData))
                }
            }
        }
    }
}