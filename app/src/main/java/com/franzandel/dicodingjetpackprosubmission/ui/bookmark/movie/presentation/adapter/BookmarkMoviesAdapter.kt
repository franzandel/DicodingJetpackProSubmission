package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemBookmarkMoviesBinding
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.diffcallback.BookmarkMoviesDiffCallback
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.viewholder.BookmarkMoviesVH

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

class BookmarkMoviesAdapter(
    private val onDeleteClick: (bookmarkMovieResponse: BookmarkMovieResponse) -> Unit
) : PagedListAdapter<BookmarkMovieResponse, BookmarkMoviesVH>(BookmarkMoviesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkMoviesVH {
        val itemBookmarkMoviesBinding =
            ItemBookmarkMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkMoviesVH(itemBookmarkMoviesBinding)
    }

    override fun onBindViewHolder(holder: BookmarkMoviesVH, position: Int) {
        val bookmarkMovieResponse = getItem(position)
        bookmarkMovieResponse?.let {
            holder.bind(it, onDeleteClick)
        }
    }
}