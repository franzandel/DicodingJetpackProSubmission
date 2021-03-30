package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.data.consts.PaginationConsts
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
    private val responseMapper: BaseMapper<BookmarkMovieDTO, BookmarkMovieResponse>,
    private val responsesMapper: BaseMapper<DataSource.Factory<Int, BookmarkMovieDTO>, DataSource.Factory<Int, BookmarkMovieResponse>>
) : BookmarkMovieRepository {

    override suspend fun getAll(): LiveData<PagedList<BookmarkMovieResponse>> {
        val mappedDataSource = responsesMapper.map(dao.getBookmarkMovies())
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(PaginationConsts.PAGE_SIZE)
            .setPageSize(PaginationConsts.PAGE_SIZE)
            .build()

        return LivePagedListBuilder(mappedDataSource, config).build()
    }

    override suspend fun get(id: Int): BookmarkMovieResponse? {
        val bookmarkMovieDTO = dao.getBookmarkMovie(id)

        return if (bookmarkMovieDTO == null)
            null
        else
            responseMapper.map(bookmarkMovieDTO)
    }

    override suspend fun add(bookmarkMovieRequest: BookmarkMovieRequest): Long {
        val bookmarkMovieDTO = requestMapper.map(bookmarkMovieRequest)
        return dao.insertBookmarkMovie(bookmarkMovieDTO)
    }

    override suspend fun delete(id: Int): Int = dao.deleteBookmarkMovie(id)
}