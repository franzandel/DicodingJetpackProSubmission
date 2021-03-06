package com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.franzandel.dicodingjetpackprosubmission.base.BaseAdapter
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDetailBinding
import com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.viewholder.DetailMovieVH
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation.diffcallback.MoviesDiffCallback

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class DetailMovieAdapter(private val context: Context) :
    BaseAdapter<Movie, DetailMovieVH, ItemDetailBinding>(MoviesDiffCallback()) {

    override fun getViewBinding(parent: ViewGroup): ItemDetailBinding =
        ItemDetailBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun getViewHolder(viewBinding: ItemDetailBinding): DetailMovieVH =
        DetailMovieVH(viewBinding)

    override fun onBindViewHolder(holder: DetailMovieVH, position: Int) {
        holder.bind(currentList)
    }
}