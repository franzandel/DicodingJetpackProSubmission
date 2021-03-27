package com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.mapper

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 27/03/21.
 * Android Engineer
 */

@ActivityScoped
class TvShowBookmarkTvShowMapper @Inject constructor() :
    BaseMapper<TvShow, BookmarkTvShowRequest>() {

    override fun map(dataModel: TvShow): BookmarkTvShowRequest = with(dataModel) {
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