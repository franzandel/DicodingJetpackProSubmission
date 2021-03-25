package com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.mapper

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieDTO
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieRequest
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@ActivityScoped
class FavoriteMovieRequestMapper @Inject constructor() :
    BaseMapper<FavoriteMovieRequest, FavoriteMovieDTO>() {

    override fun map(dataModel: FavoriteMovieRequest): FavoriteMovieDTO = with(dataModel) {
        FavoriteMovieDTO(
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
}