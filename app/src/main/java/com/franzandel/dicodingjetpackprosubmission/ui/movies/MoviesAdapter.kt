package com.franzandel.dicodingjetpackprosubmission.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.franzandel.dicodingjetpackprosubmission.base.BaseAdapter
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDashboardBinding

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class MoviesAdapter(private val context: Context) :
    BaseAdapter<Movie, MoviesVH, ItemDashboardBinding>(MoviesDiffCallback()) {

    override fun getViewBinding(parent: ViewGroup): ItemDashboardBinding =
        ItemDashboardBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun getViewHolder(viewBinding: ItemDashboardBinding): MoviesVH = MoviesVH(viewBinding)

    override fun onBindViewHolder(holder: MoviesVH, position: Int) {
        holder.bind(currentList)
    }
}