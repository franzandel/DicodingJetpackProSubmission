package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.mapper

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@ActivityScoped
class BookmarkMovieResponseDTOMapper @Inject constructor() :
    BaseMapper<BookmarkMovieDTO, BookmarkMovieResponse>() {

    override fun map(dataModel: BookmarkMovieDTO): BookmarkMovieResponse =
        with(dataModel) {
            BookmarkMovieResponse(
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