package com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MovieDTO

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class MoviesDiffCallback : DiffUtil.ItemCallback<MovieDTO>() {

    override fun areItemsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean =
        oldItem == newItem
}