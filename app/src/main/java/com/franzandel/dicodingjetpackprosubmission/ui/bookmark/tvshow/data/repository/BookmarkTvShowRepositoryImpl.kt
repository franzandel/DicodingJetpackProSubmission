package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.local.BookmarkTvShowLocalDataSource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BookmarkTvShowRepositoryImpl @Inject constructor(
    private val localDataSource: BookmarkTvShowLocalDataSource
) : BookmarkTvShowRepository {

    override suspend fun getAll(sortChoice: SortChoice): LiveData<PagedList<BookmarkTvShowResponse>> =
        localDataSource.getAll(sortChoice)

    override suspend fun get(id: Int): BookmarkTvShowResponse? = localDataSource.get(id)

    override suspend fun add(bookmarkTvShowRequest: BookmarkTvShowRequest): Long =
        localDataSource.add(bookmarkTvShowRequest)

    override suspend fun delete(id: Int): Int = localDataSource.delete(id)
}