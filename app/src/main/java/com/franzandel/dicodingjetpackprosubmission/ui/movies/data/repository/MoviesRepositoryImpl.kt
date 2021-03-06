package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseNetworkRepository
import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import com.franzandel.dicodingjetpackprosubmission.external.ErrorCodeData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MoviesResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.TmdbErrorResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.remote.MoviesNetwork
import com.google.gson.Gson

class MoviesRepositoryImpl(private val moviesNetwork: MoviesNetwork, gson: Gson) :
    BaseNetworkRepository(gson), MoviesRepository {

    override suspend fun getMovies(): LiveData<Resource<MoviesResponseDTO>> {
        val moviesResponse = MutableLiveData<Resource<MoviesResponseDTO>>()

        return withTryCatch(moviesResponse) {
            moviesNetwork.getMoviesFromAPI(AppConsts.apiKey).apply {
                if (isSuccessful) {
                    moviesResponse.postValue(Resource.success(body()))
                } else {
                    val movieErrorResponse =
                        getParsedErrorModel(errorBody(), TmdbErrorResponseDTO::class.java)
                    val errorMessage = movieErrorResponse.statusMessage
                    val errorCodeData = ErrorCodeData(code(), errorMessage)
                    moviesResponse.postValue(Resource.error(null, errorCodeData))
                }
            }
        }
    }
}