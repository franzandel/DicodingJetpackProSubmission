package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity

import com.google.gson.annotations.SerializedName

data class MoviesResponseDTO(
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieDTO>,
    val totalPages: Int,
    val totalResults: Int
)