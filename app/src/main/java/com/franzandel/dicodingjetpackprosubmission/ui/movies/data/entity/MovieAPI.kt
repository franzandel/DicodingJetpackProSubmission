package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity

data class MovieAPI(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)