package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.mapper

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@ActivityScoped
class BookmarkTvShowsResponseDTOMapper @Inject constructor() :
    BaseMapper<List<BookmarkTvShowDTO>, List<BookmarkTvShowResponse>>() {

    override fun map(dataModel: List<BookmarkTvShowDTO>): List<BookmarkTvShowResponse> =
        dataModel.map {
            with(it) {
                BookmarkTvShowResponse(
                    id = id,
                    backdropPath = backdropPath,
                    firstAirDate = firstAirDate,
                    name = name,
                    originalLanguage = originalLanguage,
                    originalName = originalName,
                    overview = overview,
                    popularity = popularity,
                    posterPath = posterPath,
                    voteAverage = voteAverage,
                    voteCount = voteCount
                )
            }
        }
}