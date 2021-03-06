package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse

/**
 * Created by Franz Andel on 01/04/21.
 * Android Engineer
 */

interface BookmarkMovieLocalDataSource {
    suspend fun getAll(sortChoice: SortChoice): LiveData<PagedList<BookmarkMovieResponse>>
    suspend fun get(id: Int): BookmarkMovieResponse?
    suspend fun add(bookmarkMovieRequest: BookmarkMovieRequest): Long
    suspend fun delete(id: Int): Int
}