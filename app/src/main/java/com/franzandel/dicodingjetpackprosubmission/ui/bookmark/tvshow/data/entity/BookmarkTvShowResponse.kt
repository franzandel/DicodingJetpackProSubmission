package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity

/**
 * Created by Franz Andel on 27/03/21.
 * Android Engineer
 */

data class BookmarkTvShowResponse(
    val backdropPath: String,
    val firstAirDate: String,
    val id: Int,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)