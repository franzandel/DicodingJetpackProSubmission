package com.franzandel.dicodingjetpackprosubmission.ui.detail.vh

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDetailBinding
import com.franzandel.dicodingjetpackprosubmission.ui.detail.DetailFragmentDirections
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class DetailMovieVH(private val itemDetailBinding: ItemDetailBinding) :
    RecyclerView.ViewHolder(itemDetailBinding.root) {

    fun bind(movies: List<Movie>) {
        with(itemDetailBinding) {
            val movie = movies[adapterPosition]

            val imageUrl = AppConsts.baseUrlImage + movies[adapterPosition].posterPath
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_image_not_found)
                .into(ivItemDetailMovie)

            tvItemDetailTitle.text = movie.title
            tvItemDetailGenre.text = movie.voteCount.toString()

            cvItemMovie.setOnClickListener {
                val navDirections =
                    DetailFragmentDirections.actionDetailFragmentSelf()
                navDirections.movies = movies.toTypedArray()
                navDirections.currentPosition = adapterPosition
                findNavController(root).navigate(navDirections)
            }
        }
    }
}