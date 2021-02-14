package com.franzandel.dicodingjetpackprosubmission.ui.movies

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemMovieBinding

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class MoviesVH(private val itemMovieBinding: ItemMovieBinding) :
    RecyclerView.ViewHolder(itemMovieBinding.root) {

    fun bind(movie: Movie) {
        with(itemMovieBinding) {
            ivItemMovie.setImageResource(movie.image)
            tvItemTitle.text = movie.title
            tvItemDescription.text = movie.desription

            cvItemMovie.setOnClickListener {
                val navDirections =
                    MoviesFragmentDirections.actionNavigationMoviesToDetailFragment()
                navDirections.movie = movie
                findNavController(root).navigate(navDirections)
            }
        }
    }
}