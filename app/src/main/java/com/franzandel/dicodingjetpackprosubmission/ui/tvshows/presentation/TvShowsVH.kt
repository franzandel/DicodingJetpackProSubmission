package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDashboardBinding
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class TvShowsVH(private val itemDashboardBinding: ItemDashboardBinding) :
    RecyclerView.ViewHolder(itemDashboardBinding.root) {

    fun bind(tvShows: List<TvShow>) {
        with(itemDashboardBinding) {
            val imageUrl = AppConsts.baseUrlImage + tvShows[adapterPosition].posterPath

            Glide.with(itemView.context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_image_not_found)
                .into(ivItemMovie)

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