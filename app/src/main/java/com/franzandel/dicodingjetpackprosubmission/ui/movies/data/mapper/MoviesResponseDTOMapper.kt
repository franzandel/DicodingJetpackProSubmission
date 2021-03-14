package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.mapper

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MoviesResponseDTO
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 06/03/21.
 * Android Engineer
 */

@ActivityScoped
class MoviesResponseDTOMapper @Inject constructor() : BaseMapper<MoviesResponseDTO, List<Movie>>() {

    override fun map(dataModel: MoviesResponseDTO): List<Movie> = dataModel.movies.map {
        Movie(
            adult = it.adult,
            backdropPath = it.backdropPath,
            genreIds = it.genreIds,
            id = it.id,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount
        )
    }
}