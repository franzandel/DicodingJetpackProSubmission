package com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDashboardBinding
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MovieDTO

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class MoviesVH(private val itemDashboardBinding: ItemDashboardBinding) :
    RecyclerView.ViewHolder(itemDashboardBinding.root) {

    fun bind(movies: List<MovieDTO>) {
        with(itemDashboardBinding) {
            val imageUrl = AppConsts.baseUrlImage + movies[adapterPosition].posterPath

            Glide.with(itemView.context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_image_not_found)
                .into(ivItemMovie)

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