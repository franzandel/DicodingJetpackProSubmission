package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.dicodingjetpackprosubmission.data.entity.TvShow

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class TvShowsDiffCallback : DiffUtil.ItemCallback<TvShow>() {

    override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
        oldItem == newItem
}