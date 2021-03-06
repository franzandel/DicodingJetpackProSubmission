package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShowDTO

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class TvShowsDiffCallback : DiffUtil.ItemCallback<TvShowDTO>() {

    override fun areItemsTheSame(oldItem: TvShowDTO, newItem: TvShowDTO): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TvShowDTO, newItem: TvShowDTO): Boolean =
        oldItem == newItem
}