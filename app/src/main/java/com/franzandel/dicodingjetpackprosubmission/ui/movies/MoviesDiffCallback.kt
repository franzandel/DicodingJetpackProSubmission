package com.franzandel.dicodingjetpackprosubmission.ui.movies

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Result

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class MoviesDiffCallback : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem == newItem
}