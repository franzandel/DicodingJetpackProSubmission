package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.mapper

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 27/03/21.
 * Android Engineer
 */

@ActivityScoped
class BookmarkTvShowRequestDTOMapper @Inject constructor() :
    BaseMapper<BookmarkTvShowRequest, BookmarkTvShowDTO>() {

    override fun map(dataModel: BookmarkTvShowRequest): BookmarkTvShowDTO = with(dataModel) {
        BookmarkTvShowDTO(
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