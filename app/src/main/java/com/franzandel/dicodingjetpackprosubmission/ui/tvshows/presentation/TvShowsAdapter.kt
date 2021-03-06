package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.franzandel.dicodingjetpackprosubmission.base.BaseAdapter
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDashboardBinding
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.Result

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class TvShowsAdapter(private val context: Context) :
    BaseAdapter<Result, TvShowsVH, ItemDashboardBinding>(
        TvShowsDiffCallback()
    ) {

    override fun getViewBinding(parent: ViewGroup): ItemDashboardBinding =
        ItemDashboardBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun getViewHolder(viewBinding: ItemDashboardBinding): TvShowsVH =
        TvShowsVH(
            viewBinding
        )

    override fun onBindViewHolder(holder: TvShowsVH, position: Int) {
        holder.bind(currentList)
    }
}