package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity

import com.google.gson.annotations.SerializedName

data class TmdbErrorResponseDTO(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    val success: Boolean
)