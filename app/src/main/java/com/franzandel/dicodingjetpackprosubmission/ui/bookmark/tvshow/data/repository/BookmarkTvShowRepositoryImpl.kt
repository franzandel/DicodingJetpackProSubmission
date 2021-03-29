package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.dao.BookmarkTvShowDao
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BookmarkTvShowRepositoryImpl @Inject constructor(
    private val dao: BookmarkTvShowDao,
    private val requestMapper: BaseMapper<BookmarkTvShowRequest, BookmarkTvShowDTO>,
    private val responseMapper: BaseMapper<BookmarkTvShowDTO, BookmarkTvShowResponse>,
    private val responsesMapper: BaseMapper<List<BookmarkTvShowDTO>, List<BookmarkTvShowResponse>>
) : BookmarkTvShowRepository {

    override suspend fun getAll(): LiveData<List<BookmarkTvShowResponse>> =
        Transformations.map(dao.getBookmarkTvShows()) { bookmarkTvShowsDTO ->
            responsesMapper.map(bookmarkTvShowsDTO)
        }

    override suspend fun get(id: Int): BookmarkTvShowResponse? {
        val bookmarkTvShowDTO = dao.getBookmarkTvShow(id)

        return if (bookmarkTvShowDTO == null)
            null
        else
            responseMapper.map(bookmarkTvShowDTO)
    }

    override suspend fun add(bookmarkTvShowRequest: BookmarkTvShowRequest): Long {
        val bookmarkTvShowDTO = requestMapper.map(bookmarkTvShowRequest)
        return dao.insertBookmarkTvShow(bookmarkTvShowDTO)
    }

    override suspend fun delete(id: Int): Int = dao.deleteBookmarkTvShow(id)
}