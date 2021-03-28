package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.consts.ApiConsts
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemBookmarkMoviesBinding
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

class BookmarkMoviesVH(private val itemBookmarkMoviesBinding: ItemBookmarkMoviesBinding) :
    RecyclerView.ViewHolder(itemBookmarkMoviesBinding.root) {

    fun bind(bookmarkMovies: BookmarkMovieResponse) {
        with(itemBookmarkMoviesBinding) {
            val imageUrl = ApiConsts.baseUrlImage + bookmarkMovies.posterPath

            Glide.with(itemView.context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_image_not_found)
                .into(ivBookmarkMovies)

            tvTitle.text = bookmarkMovies.title
            tvReleaseDate.text = bookmarkMovies.releaseDate
            tvOverview.text = bookmarkMovies.overview

            cpiVoteAverage.progress = bookmarkMovies.voteAverage.toInt()
            tvVoteAverage.text = bookmarkMovies.voteAverage.toString()
        }
    }
}