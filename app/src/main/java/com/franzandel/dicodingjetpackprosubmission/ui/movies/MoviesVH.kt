package com.franzandel.dicodingjetpackprosubmission.ui.movies

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDashboardMovieBinding

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class MoviesVH(private val itemDashboardMovieBinding: ItemDashboardMovieBinding) :
    RecyclerView.ViewHolder(itemDashboardMovieBinding.root) {

    fun bind(movies: List<Movie>) {
        with(itemDashboardMovieBinding) {
            ivItemMovie.setImageResource(movies[adapterPosition].image)

            cvItemMovie.setOnClickListener {
                val navDirections =
                    MoviesFragmentDirections.actionNavigationMoviesToDetailFragment()
                navDirections.movies = movies.toTypedArray()
                navDirections.currentPosition = adapterPosition
                findNavController(root).navigate(navDirections)
            }
        }
    }
}