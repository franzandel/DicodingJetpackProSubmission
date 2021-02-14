package com.franzandel.dicodingjetpackprosubmission.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDashboardMovieBinding

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class MoviesAdapter(private val context: Context) :
    ListAdapter<Movie, MoviesVH>(MoviesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH {
        val itemMovieBinding =
            ItemDashboardMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return MoviesVH(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MoviesVH, position: Int) {
        holder.bind(currentList)
    }
}