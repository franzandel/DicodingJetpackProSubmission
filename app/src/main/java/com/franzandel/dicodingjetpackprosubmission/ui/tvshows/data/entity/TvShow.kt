package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Franz Andel on 06/03/21.
 * Android Engineer
 */

@Parcelize
data class TvShow(
    val backdropPath: String,
    val firstAirDate: String,
    val genreIds: List<Int>,
    val id: Int,
    val name: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
) : Parcelable