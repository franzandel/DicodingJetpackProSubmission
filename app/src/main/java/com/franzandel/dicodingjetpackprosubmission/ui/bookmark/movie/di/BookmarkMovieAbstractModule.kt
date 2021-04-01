package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.di

import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.local.BookmarkMovieLocalDataSource
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.local.BookmarkMovieLocalDataSourceImpl
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository.BookmarkMovieRepository
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository.BookmarkMovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@Module
@InstallIn(ActivityComponent::class)
abstract class BookmarkMovieAbstractModule {

    @Binds
    abstract fun bindBookmarkMovieRepository(bookmarkMovieRepositoryImpl: BookmarkMovieRepositoryImpl): BookmarkMovieRepository

    @Binds
    abstract fun bindBookmarkMovieLocalDatasource(bookmarkMovieLocalDatasourceImpl: BookmarkMovieLocalDataSourceImpl): BookmarkMovieLocalDataSource
}