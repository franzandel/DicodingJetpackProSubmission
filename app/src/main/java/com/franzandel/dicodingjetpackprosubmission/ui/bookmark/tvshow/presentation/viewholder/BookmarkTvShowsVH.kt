package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.consts.ApiConsts
import com.franzandel.dicodingjetpackprosubmission.databinding.ItemBookmarkTvShowsBinding
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

class BookmarkTvShowsVH(private val itemBookmarkTvShowsBinding: ItemBookmarkTvShowsBinding) :
    RecyclerView.ViewHolder(itemBookmarkTvShowsBinding.root) {

    fun bind(
        bookmarkTvShowResponse: BookmarkTvShowResponse,
        onDeleteClick: (bookmarkTvShowResponse: BookmarkTvShowResponse) -> Unit
    ) {
        with(itemBookmarkTvShowsBinding) {
            val context = itemView.context
            val imageUrl = ApiConsts.baseUrlImage + bookmarkTvShowResponse.posterPath

            Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_image_not_found)
                .into(ivBookmarkTvShow)

            tvName.text = bookmarkTvShowResponse.name
            tvFirstAirDate.text = bookmarkTvShowResponse.firstAirDate
            tvOverview.text = bookmarkTvShowResponse.overview

            cpiVoteAverage.progress = bookmarkTvShowResponse.voteAverage.toInt()
            tvVoteAverage.text = bookmarkTvShowResponse.voteAverage.toString()

            ivDeleteBookmarkTvShow.setOnClickListener {
                onDeleteClick.invoke(bookmarkTvShowResponse)
            }
        }
    }
}