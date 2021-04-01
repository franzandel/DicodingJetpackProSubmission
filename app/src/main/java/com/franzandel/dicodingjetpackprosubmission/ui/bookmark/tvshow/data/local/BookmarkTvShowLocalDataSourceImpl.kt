package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SimpleSQLiteQuery
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.data.consts.PaginationConsts
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.dao.BookmarkTvShowDao
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BookmarkTvShowLocalDataSourceImpl @Inject constructor(
    private val dao: BookmarkTvShowDao,
    private val requestMapper: BaseMapper<BookmarkTvShowRequest, BookmarkTvShowDTO>,
    private val responseMapper: BaseMapper<BookmarkTvShowDTO, BookmarkTvShowResponse>,
    private val responsesMapper: BaseMapper<DataSource.Factory<Int, BookmarkTvShowDTO>, DataSource.Factory<Int, BookmarkTvShowResponse>>
) : BookmarkTvShowLocalDataSource {

    override suspend fun getAll(sortChoice: SortChoice): LiveData<PagedList<BookmarkTvShowResponse>> {
        fun getSortedQuery(): SimpleSQLiteQuery {
            val query = StringBuilder().append("SELECT * FROM tbl_bookmark_tv_show ")
            when (sortChoice) {
                SortChoice.TITLE -> query.append("ORDER BY name ASC")
                SortChoice.RATING -> query.append("ORDER BY vote_average ASC")
            }
            return SimpleSQLiteQuery(query.toString())
        }

        val mappedDataSource = responsesMapper.map(dao.getBookmarkTvShows(getSortedQuery()))
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(PaginationConsts.PAGE_SIZE)
            .setPageSize(PaginationConsts.PAGE_SIZE)
            .build()

        return LivePagedListBuilder(mappedDataSource, config).build()
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