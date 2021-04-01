package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.local.BookmarkMovieLocalDataSource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class BookmarkMovieRepositoryImpl @Inject constructor(
    private val localDataSource: BookmarkMovieLocalDataSource
) : BookmarkMovieRepository {

    override suspend fun getAll(sortChoice: SortChoice): LiveData<PagedList<BookmarkMovieResponse>> =
        localDataSource.getAll(sortChoice)

    override suspend fun get(id: Int): BookmarkMovieResponse? = localDataSource.get(id)

    override suspend fun add(bookmarkMovieRequest: BookmarkMovieRequest): Long =
        localDataSource.add(bookmarkMovieRequest)

    override suspend fun delete(id: Int): Int = localDataSource.delete(id)
}