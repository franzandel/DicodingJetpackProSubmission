package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.mapper

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

@ActivityScoped
class BookmarkMovieResponseRequestMapper @Inject constructor() :
    BaseMapper<BookmarkMovieResponse, BookmarkMovieRequest>() {

    override fun map(dataModel: BookmarkMovieResponse): BookmarkMovieRequest = with(dataModel) {
        BookmarkMovieRequest(
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