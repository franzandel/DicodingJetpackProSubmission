package com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.mapper

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@ActivityScoped
class MovieFavoriteMovieMapper @Inject constructor() : BaseMapper<Movie, FavoriteMovieRequest>() {

    override fun map(dataModel: Movie): FavoriteMovieRequest = with(dataModel) {
        FavoriteMovieRequest(
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