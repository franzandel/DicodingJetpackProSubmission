package com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieDTO
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@ActivityScoped
class FavoriteMoviesResponseDTOMapper @Inject constructor() :
    BaseMapper<LiveData<List<FavoriteMovieDTO>>, LiveData<List<FavoriteMovieResponse>>>() {

    override fun map(dataModel: LiveData<List<FavoriteMovieDTO>>): LiveData<List<FavoriteMovieResponse>> {
        val mutableFavoriteMovies = MutableLiveData<List<FavoriteMovieResponse>>()
        val favoriteMoviesResponse = dataModel.value?.map {
            with(it) {
                FavoriteMovieResponse(
                    id = id,
                    adult = adult,
                    backdropPath = backdropPath,
                    originalLanguage = originalLanguage,
                    originalTitle = originalTitle,
                    overview = overview,
                    popularity = popularity,
                    posterPath = posterPath,
                    releaseDate = releaseDate,
                    title = title,
                    video = video,
                    voteAverage = voteAverage,
                    voteCount = voteCount
                )
            }
        } ?: listOf()
        mutableFavoriteMovies.postValue(favoriteMoviesResponse)

        return mutableFavoriteMovies
    }
}