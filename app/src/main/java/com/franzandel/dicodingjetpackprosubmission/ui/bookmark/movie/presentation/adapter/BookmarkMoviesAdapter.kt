package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.franzandel.dicodingjetpackprosubmission.base.BaseAdapter
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemBookmarkMoviesBinding
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.diffcallback.BookmarkMoviesDiffCallback
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.viewholder.BookmarkMoviesVH

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

class BookmarkMoviesAdapter(
    private val context: Context,
    private val onDeleteClick: (bookmarkMovieResponse: BookmarkMovieResponse) -> Unit
) : BaseAdapter<BookmarkMovieResponse, BookmarkMoviesVH, ItemBookmarkMoviesBinding>(
    BookmarkMoviesDiffCallback()
) {

    override fun getViewBinding(parent: ViewGroup): ItemBookmarkMoviesBinding =
        ItemBookmarkMoviesBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun getViewHolder(viewBinding: ItemBookmarkMoviesBinding): BookmarkMoviesVH =
        BookmarkMoviesVH(viewBinding)

    override fun onBindViewHolder(holder: BookmarkMoviesVH, position: Int) {
        holder.bind(currentList[position], onDeleteClick)
    }
}