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

    fun bind(
        bookmarkMovieResponse: BookmarkMovieResponse,
        onDeleteClick: (bookmarkMovieResponse: BookmarkMovieResponse) -> Unit
    ) {
        with(itemBookmarkMoviesBinding) {
            val context = itemView.context
            val imageUrl = ApiConsts.baseUrlImage + bookmarkMovieResponse.posterPath

            Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_image_not_found)
                .into(ivBookmarkMovies)

            tvTitle.text = bookmarkMovieResponse.title
            tvReleaseDate.text = bookmarkMovieResponse.releaseDate
            tvOverview.text = bookmarkMovieResponse.overview

            cpiVoteAverage.progress = bookmarkMovieResponse.voteAverage.toInt()
            tvVoteAverage.text = bookmarkMovieResponse.voteAverage.toString()

            ivDeleteBookmarkMovie.setOnClickListener {
                onDeleteClick.invoke(bookmarkMovieResponse)
            }
        }
    }
}