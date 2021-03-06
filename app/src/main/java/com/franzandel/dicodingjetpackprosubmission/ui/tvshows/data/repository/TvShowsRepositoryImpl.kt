package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseNetworkRepository
import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import com.franzandel.dicodingjetpackprosubmission.external.ErrorCodeData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.TmdbErrorResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShowsResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.remote.TvShowsNetwork
import com.google.gson.Gson

class TvShowsRepositoryImpl(private val network: TvShowsNetwork, gson: Gson) :
    BaseNetworkRepository(gson), TvShowsRepository {

    override suspend fun getTvShows(): LiveData<Resource<TvShowsResponseDTO>> {
        val tvShowsResponse = MutableLiveData<Resource<TvShowsResponseDTO>>()

        return withTryCatch(tvShowsResponse) {
            network.getTvShowsFromAPI(AppConsts.apiKey).apply {
                if (isSuccessful) {
                    tvShowsResponse.postValue(Resource.success(body()))
                } else {
                    val tvShowsErrorResponse =
                        getParsedErrorModel(errorBody(), TmdbErrorResponseDTO::class.java)
                    val errorMessage = tvShowsErrorResponse.statusMessage
                    val errorCodeData = ErrorCodeData(code(), errorMessage)
                    tvShowsResponse.postValue(Resource.error(null, errorCodeData))
                }
            }
        }
    }
}