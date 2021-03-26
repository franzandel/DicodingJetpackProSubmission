package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository

import androidx.lifecycle.LiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.dao.BookmarkMovieDao
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BookmarkMovieRepositoryImpl @Inject constructor(
    private val dao: BookmarkMovieDao,
    private val requestMapper: BaseMapper<BookmarkMovieRequest, BookmarkMovieDTO>,
    private val responsesMapper: BaseMapper<LiveData<List<BookmarkMovieDTO>>, LiveData<List<BookmarkMovieResponse>>>
) : BookmarkMovieRepository {

    override suspend fun add(bookmarkMovieRequest: BookmarkMovieRequest): Long {
        val bookmarkMovieDTO = requestMapper.map(bookmarkMovieRequest)
        return dao.insertBookmarkMovie(bookmarkMovieDTO)
    }

    override suspend fun delete(id: Int): Int = dao.deleteBookmarkMovie(id)

    override suspend fun getAll(): LiveData<List<BookmarkMovieResponse>> =
        responsesMapper.map(dao.getBookmarkMovies())
}