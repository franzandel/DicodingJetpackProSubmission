package com.franzandel.dicodingjetpackprosubmission.ui.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.franzandel.dicodingjetpackprosubmission.base.BaseAdapter
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemDetailBinding
import com.franzandel.dicodingjetpackprosubmission.ui.detail.vh.DetailTvShowVH
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation.TvShowsDiffCallback

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

class DetailTvShowAdapter(private val context: Context) :
    BaseAdapter<TvShow, DetailTvShowVH, ItemDetailBinding>(TvShowsDiffCallback()) {

    override fun getViewBinding(parent: ViewGroup): ItemDetailBinding =
        ItemDetailBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun getViewHolder(viewBinding: ItemDetailBinding): DetailTvShowVH =
        DetailTvShowVH(
            viewBinding
        )

    override fun onBindViewHolder(holder: DetailTvShowVH, position: Int) {
        holder.bind(currentList)
    }
}