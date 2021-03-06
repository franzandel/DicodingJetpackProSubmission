package com.franzandel.dicodingjetpackprosubmission.ui.movies.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseNetworkRepository
import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import com.franzandel.dicodingjetpackprosubmission.external.ErrorCodeData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MovieAPI
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MovieErrorResponse
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.remote.MoviesNetwork
import com.google.gson.Gson

class MoviesRepositoryImpl(private val moviesNetwork: MoviesNetwork, gson: Gson) : MoviesRepository,
    BaseNetworkRepository(gson) {

    override suspend fun getMovies(): LiveData<Resource<MovieAPI>> {
        val moviesResponse = MutableLiveData<Resource<MovieAPI>>()

        return withTryCatch(moviesResponse) {
            moviesNetwork.getMoviesFromAPI(AppConsts.apiKey).apply {
                if (isSuccessful) {
                    moviesResponse.postValue(Resource.success(body()))
                } else {
                    val movieErrorResponse =
                        getParsedErrorModel(errorBody(), MovieErrorResponse::class.java)
                    val errorMessage = movieErrorResponse.status_message
                    val errorCodeData = ErrorCodeData(code(), errorMessage)
                    moviesResponse.postValue(Resource.error(null, errorCodeData))
                }
            }
        }
    }
}