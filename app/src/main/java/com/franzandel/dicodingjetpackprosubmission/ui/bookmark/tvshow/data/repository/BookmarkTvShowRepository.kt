package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.repository

import androidx.lifecycle.LiveData
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse

/**
 * Created by Franz Andel on 27/03/21.
 * Android Engineer
 */

interface BookmarkTvShowRepository {
    suspend fun getAll(): LiveData<List<BookmarkTvShowResponse>>
    suspend fun get(id: Int): BookmarkTvShowResponse?
    suspend fun add(bookmarkTvShowRequest: BookmarkTvShowRequest): Long
    suspend fun delete(id: Int): Int
}