package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity

data class TmdbErrorResponse(
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)