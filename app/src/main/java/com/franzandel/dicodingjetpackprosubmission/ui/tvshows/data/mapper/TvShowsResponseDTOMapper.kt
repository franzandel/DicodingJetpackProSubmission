package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.mapper

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShowsResponseDTO
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 06/03/21.
 * Android Engineer
 */

@ActivityScoped
class TvShowsResponseDTOMapper @Inject constructor() :
    BaseMapper<TvShowsResponseDTO, List<TvShow>>() {

    override fun map(dataModel: TvShowsResponseDTO): List<TvShow> = dataModel.tvShows.map {
        TvShow(
            backdropPath = it.backdropPath,
            firstAirDate = it.firstAirDate,
            genreIds = it.genreIds,
            id = it.id,
            name = it.name,
            originCountry = it.originCountry,
            originalLanguage = it.originalLanguage,
            originalName = it.originalName,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount
        )
    }
}