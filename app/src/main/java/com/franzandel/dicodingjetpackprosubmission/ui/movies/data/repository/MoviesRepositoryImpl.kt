package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.base.BaseNetworkRepository
import com.franzandel.dicodingjetpackprosubmission.data.consts.ApiConsts
import com.franzandel.dicodingjetpackprosubmission.external.ErrorCodeData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MoviesResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.TmdbErrorResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.remote.MoviesNetwork
import com.google.gson.Gson
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MoviesRepositoryImpl @Inject constructor(
    private val moviesNetwork: MoviesNetwork,
    private val mapper: BaseMapper<MoviesResponseDTO, List<Movie>>,
    gson: Gson
) : BaseNetworkRepository(gson), MoviesRepository {

    override suspend fun getMovies(): LiveData<Resource<List<Movie>>> {
        val moviesResponse = MutableLiveData<Resource<List<Movie>>>()

        return withTryCatch(moviesResponse) {
            moviesNetwork.getMovies(ApiConsts.apiKey).apply {
                if (isSuccessful) {
                    val movies = mapper.map(body()!!)
                    moviesResponse.postValue(Resource.success(movies))
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