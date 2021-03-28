package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

class BookmarkMoviesDiffCallback : DiffUtil.ItemCallback<BookmarkMovieResponse>() {

    override fun areItemsTheSame(
        oldItem: BookmarkMovieResponse,
        newItem: BookmarkMovieResponse
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: BookmarkMovieResponse,
        newItem: BookmarkMovieResponse
    ): Boolean =
        oldItem == newItem
}