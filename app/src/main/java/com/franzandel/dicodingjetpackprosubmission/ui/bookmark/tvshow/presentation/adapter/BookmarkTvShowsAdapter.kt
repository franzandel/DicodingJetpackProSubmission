package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemBookmarkTvShowsBinding
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.diffcallback.BookmarkTvShowsDiffCallback
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.viewholder.BookmarkTvShowsVH

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

class BookmarkTvShowsAdapter(
    private val onDeleteClick: (bookmarkTvShowResponse: BookmarkTvShowResponse) -> Unit
) : PagedListAdapter<BookmarkTvShowResponse, BookmarkTvShowsVH>(BookmarkTvShowsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkTvShowsVH {
        val itemBookmarkTvShowsBinding =
            ItemBookmarkTvShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkTvShowsVH(itemBookmarkTvShowsBinding)
    }

    override fun onBindViewHolder(holder: BookmarkTvShowsVH, position: Int) {
        val bookmarkTvShowResponse = getItem(position)
        bookmarkTvShowResponse?.let {
            holder.bind(it, onDeleteClick)
        }
    }
}