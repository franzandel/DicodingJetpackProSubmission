package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.franzandel.dicodingjetpackprosubmission.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDashboardBinding

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class TvShowsVH(private val itemDashboardBinding: ItemDashboardBinding) :
    RecyclerView.ViewHolder(itemDashboardBinding.root) {

    fun bind(tvShows: List<TvShow>) {
        with(itemDashboardBinding) {
            ivItemMovie.setImageResource(tvShows[adapterPosition].image)

            cvItemMovie.setOnClickListener {
                val navDirections =
                    TvShowsFragmentDirections.actionNavigationTvShowsToDetailFragment()
                navDirections.tvShows = tvShows.toTypedArray()
                navDirections.currentPosition = adapterPosition
                findNavController(root).navigate(navDirections)
            }
        }
    }
}