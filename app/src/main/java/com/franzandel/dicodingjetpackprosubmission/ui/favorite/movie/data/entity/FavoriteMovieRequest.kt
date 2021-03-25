package com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

data class FavoriteMovieRequest(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)