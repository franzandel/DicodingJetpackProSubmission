package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository

import androidx.lifecycle.LiveData
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

interface BookmarkMovieRepository {
    suspend fun add(bookmarkMovieRequest: BookmarkMovieRequest): Long
    suspend fun delete(id: Int): Int
    suspend fun getAll(): LiveData<List<BookmarkMovieResponse>>
}