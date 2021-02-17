package com.franzandel.dicodingjetpackprosubmission.ui.detail

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.franzandel.dicodingjetpackprosubmission.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDetailBinding

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class DetailTvShowVH(private val itemDetailBinding: ItemDetailBinding) :
    RecyclerView.ViewHolder(itemDetailBinding.root) {

    fun bind(tvShows: List<TvShow>) {
        with(itemDetailBinding) {
            val movie = tvShows[adapterPosition]
            ivItemDetailMovie.setImageResource(movie.image)
            tvItemDetailTitle.text = movie.title
            tvItemDetailGenre.text = movie.genre

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