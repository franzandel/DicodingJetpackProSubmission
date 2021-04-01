package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse

/**
 * Created by Franz Andel on 01/04/21.
 * Android Engineer
 */

interface BookmarkTvShowLocalDataSource {
    suspend fun getAll(sortChoice: SortChoice): LiveData<PagedList<BookmarkTvShowResponse>>
    suspend fun get(id: Int): BookmarkTvShowResponse?
    suspend fun add(bookmarkTvShowRequest: BookmarkTvShowRequest): Long
    suspend fun delete(id: Int): Int
}