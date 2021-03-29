package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.franzandel.dicodingjetpackprosubmission.base.BaseAdapter
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemBookmarkTvShowsBinding
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.diffcallback.BookmarkTvShowsDiffCallback
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.viewholder.BookmarkTvShowsVH

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

class BookmarkTvShowsAdapter(
    private val context: Context,
    private val onDeleteClick: (bookmarkTvShowResponse: BookmarkTvShowResponse) -> Unit
) : BaseAdapter<BookmarkTvShowResponse, BookmarkTvShowsVH, ItemBookmarkTvShowsBinding>(
    BookmarkTvShowsDiffCallback()
) {

    override fun getViewBinding(parent: ViewGroup): ItemBookmarkTvShowsBinding =
        ItemBookmarkTvShowsBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun getViewHolder(viewBinding: ItemBookmarkTvShowsBinding): BookmarkTvShowsVH =
        BookmarkTvShowsVH(viewBinding)

    override fun onBindViewHolder(holder: BookmarkTvShowsVH, position: Int) {
        holder.bind(currentList[position], onDeleteClick)
    }
}