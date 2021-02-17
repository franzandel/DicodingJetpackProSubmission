package com.franzandel.dicodingjetpackprosubmission.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDetailBinding
import com.franzandel.dicodingjetpackprosubmission.ui.movies.MoviesDiffCallback

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class DetailMovieAdapter(private val context: Context) :
    ListAdapter<Movie, DetailMovieVH>(MoviesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailMovieVH {
        val itemDetailBinding =
            ItemDetailBinding.inflate(LayoutInflater.from(context), parent, false)
        return DetailMovieVH(itemDetailBinding)
    }

    override fun onBindViewHolder(holder: DetailMovieVH, position: Int) {
        holder.bind(currentList)
    }
}