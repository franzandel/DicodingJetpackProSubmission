package com.franzandel.dicodingjetpackprosubmission.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.franzandel.dicodingjetpackprosubmission.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDetailBinding
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.TvShowsDiffCallback

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class DetailTvShowAdapter(private val context: Context) :
    ListAdapter<TvShow, DetailTvShowVH>(TvShowsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTvShowVH {
        val itemDetailBinding =
            ItemDetailBinding.inflate(LayoutInflater.from(context), parent, false)
        return DetailTvShowVH(itemDetailBinding)
    }

    override fun onBindViewHolder(holder: DetailTvShowVH, position: Int) {
        holder.bind(currentList)
    }
}