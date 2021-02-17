package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.franzandel.dicodingjetpackprosubmission.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDashboardBinding

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class TvShowsAdapter(private val context: Context) :
    ListAdapter<TvShow, TvShowsVH>(TvShowsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsVH {
        val itemDashboardBinding =
            ItemDashboardBinding.inflate(LayoutInflater.from(context), parent, false)
        return TvShowsVH(itemDashboardBinding)
    }

    override fun onBindViewHolder(holder: TvShowsVH, position: Int) {
        holder.bind(currentList)
    }
}