package com.franzandel.dicodingjetpackprosubmission.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

@Parcelize
data class Movie(
    val title: String,
    val desription: String,
    val image: Int,
    val genre: String,
    val releaseDate: String,
    val length: String,
    val rating: String
) : Parcelable