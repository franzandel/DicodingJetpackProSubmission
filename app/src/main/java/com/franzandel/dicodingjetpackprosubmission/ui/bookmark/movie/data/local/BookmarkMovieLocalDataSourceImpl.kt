package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SimpleSQLiteQuery
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.data.consts.PaginationConsts
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.dao.BookmarkMovieDao
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BookmarkMovieLocalDataSourceImpl @Inject constructor(
    private val dao: BookmarkMovieDao,
    private val requestMapper: BaseMapper<BookmarkMovieRequest, BookmarkMovieDTO>,
    private val responseMapper: BaseMapper<BookmarkMovieDTO, BookmarkMovieResponse>,
    private val responsesMapper: BaseMapper<DataSource.Factory<Int, BookmarkMovieDTO>, DataSource.Factory<Int, BookmarkMovieResponse>>
) : BookmarkMovieLocalDataSource {

    override suspend fun getAll(sortChoice: SortChoice): LiveData<PagedList<BookmarkMovieResponse>> {
        fun getSortedQuery(): SimpleSQLiteQuery {
            val query = StringBuilder().append("SELECT * FROM tbl_bookmark_movie ")
            when (sortChoice) {
                SortChoice.TITLE -> query.append("ORDER BY title ASC")
                SortChoice.RATING -> query.append("ORDER BY vote_average ASC")
            }
            return SimpleSQLiteQuery(query.toString())
        }

        val dataSource = dao.getBookmarkMovies(getSortedQuery())
        val mappedDataSource = responsesMapper.map(dataSource)
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