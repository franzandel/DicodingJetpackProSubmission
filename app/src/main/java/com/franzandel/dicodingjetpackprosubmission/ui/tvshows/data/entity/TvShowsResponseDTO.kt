package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity

import com.google.gson.annotations.SerializedName

data class TvShowsResponseDTO(
    val page: Int,
    @SerializedName("results")
    val tvShows: List<TvShowDTO>,
    val totalPages: Int,
    val totalResults: Int
)