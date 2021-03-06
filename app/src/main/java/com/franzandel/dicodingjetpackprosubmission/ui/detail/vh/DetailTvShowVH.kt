package com.franzandel.dicodingjetpackprosubmission.ui.detail.vh

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDetailBinding
import com.franzandel.dicodingjetpackprosubmission.ui.detail.DetailFragmentDirections
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class DetailTvShowVH(private val itemDetailBinding: ItemDetailBinding) :
    RecyclerView.ViewHolder(itemDetailBinding.root) {

    fun bind(tvShows: List<TvShow>) {
        with(itemDetailBinding) {
            val tvShow = tvShows[adapterPosition]
            val imageUrl = AppConsts.baseUrlImage + tvShow.posterPath
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_image_not_found)
                .into(ivItemDetailMovie)

            tvItemDetailTitle.text = tvShow.name
            tvItemDetailGenre.text = tvShow.voteCount.toString()

            cvItemMovie.setOnClickListener {
                val navDirections =
                    DetailFragmentDirections.actionDetailFragmentSelf()
                navDirections.tvShows = tvShows.toTypedArray()
                navDirections.currentPosition = adapterPosition
                findNavController(root).navigate(navDirections)
            }
        }
    }
}