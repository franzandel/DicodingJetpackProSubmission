package com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class MoviesDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}