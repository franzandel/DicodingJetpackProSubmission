package com.franzandel.dicodingjetpackprosubmission.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDetailMovieBinding
import com.franzandel.dicodingjetpackprosubmission.ui.movies.MoviesDiffCallback

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class DetailAdapter(private val context: Context) :
    ListAdapter<Movie, DetailVH>(MoviesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailVH {
        val itemDetailMovieBinding =
            ItemDetailMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return DetailVH(itemDetailMovieBinding)
    }

    override fun onBindViewHolder(holder: DetailVH, position: Int) {
        holder.bind(currentList)
    }
}