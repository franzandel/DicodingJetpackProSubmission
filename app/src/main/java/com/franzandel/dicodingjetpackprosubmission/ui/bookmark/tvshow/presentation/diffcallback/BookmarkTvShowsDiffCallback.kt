package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

class BookmarkTvShowsDiffCallback : DiffUtil.ItemCallback<BookmarkTvShowResponse>() {

    override fun areItemsTheSame(
        oldItem: BookmarkTvShowResponse,
        newItem: BookmarkTvShowResponse
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: BookmarkTvShowResponse,
        newItem: BookmarkTvShowResponse
    ): Boolean =
        oldItem == newItem
}