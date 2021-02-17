package com.franzandel.dicodingjetpackprosubmission.ui.detail

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDetailBinding

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class DetailMovieVH(private val itemDetailBinding: ItemDetailBinding) :
    RecyclerView.ViewHolder(itemDetailBinding.root) {

    fun bind(movies: List<Movie>) {
        with(itemDetailBinding) {
            val movie = movies[adapterPosition]
            ivItemDetailMovie.setImageResource(movie.image)
            tvItemDetailTitle.text = movie.title
            tvItemDetailGenre.text = movie.genre

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