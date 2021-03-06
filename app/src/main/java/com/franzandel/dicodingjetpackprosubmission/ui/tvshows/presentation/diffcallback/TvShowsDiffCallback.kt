package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class TvShowsDiffCallback : DiffUtil.ItemCallback<TvShow>() {

    override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
        oldItem == newItem
}