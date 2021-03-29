package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.mapper

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

@ActivityScoped
class BookmarkTvShowResponseRequestMapper @Inject constructor() :
    BaseMapper<BookmarkTvShowResponse, BookmarkTvShowRequest>() {

    override fun map(dataModel: BookmarkTvShowResponse): BookmarkTvShowRequest = with(dataModel) {
        BookmarkTvShowRequest(
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