package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.di

import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.repository.BookmarkTvShowRepository
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.repository.BookmarkTvShowRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by Franz Andel on 27/03/21.
 * Android Engineer
 */

@Module
@InstallIn(ActivityComponent::class)
abstract class BookmarkTvShowAbstractModule {

    @Binds
    abstract fun bindBookmarkTvShowRepository(bookmarkTvShowRepositoryImpl: BookmarkTvShowRepositoryImpl): BookmarkTvShowRepository
}