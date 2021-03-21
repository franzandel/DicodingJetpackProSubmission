package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.base.BaseNetworkRepository
import com.franzandel.dicodingjetpackprosubmission.data.consts.ApiConsts
import com.franzandel.dicodingjetpackprosubmission.external.ErrorCodeData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.TmdbErrorResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShowsResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.remote.TvShowsNetwork
import com.google.gson.Gson
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TvShowsRepositoryImpl @Inject constructor(
    private val network: TvShowsNetwork,
    private val mapper: BaseMapper<TvShowsResponseDTO, List<TvShow>>,
    gson: Gson
) : BaseNetworkRepository(gson), TvShowsRepository {

    override suspend fun getTvShows(): LiveData<Resource<List<TvShow>>> {
        val tvShowsResponse = MutableLiveData<Resource<List<TvShow>>>()

        return withTryCatch(tvShowsResponse) {
            network.getTvShows(ApiConsts.apiKey).apply {
                if (isSuccessful) {
                    val tvShows = mapper.map(body()!!)
                    tvShowsResponse.postValue(Resource.success(tvShows))
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