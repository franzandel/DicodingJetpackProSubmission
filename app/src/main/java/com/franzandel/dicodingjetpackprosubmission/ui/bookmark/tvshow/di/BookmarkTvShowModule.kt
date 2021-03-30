package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.di

import androidx.paging.DataSource
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.data.local.BookmarkDatabase
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.dao.BookmarkTvShowDao
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.mapper.BookmarkTvShowRequestDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.mapper.BookmarkTvShowResponseDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.mapper.BookmarkTvShowsResponseDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.mapper.BookmarkTvShowResponseRequestMapper
import com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.mapper.TvShowBookmarkTvShowMapper
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Created by Franz Andel on 27/03/21.
 * Android Engineer
 */

@Module
@InstallIn(ActivityComponent::class)
object BookmarkTvShowModule {

    @Provides
    @ActivityScoped
    fun provideBookmarkTvShowDao(bookmarkDatabase: BookmarkDatabase): BookmarkTvShowDao =
        bookmarkDatabase.bookmarkTvShowDao()

    @Provides
    fun provideBookmarkTvShowRequestDTOMapper(): BaseMapper<BookmarkTvShowRequest, BookmarkTvShowDTO> =
        BookmarkTvShowRequestDTOMapper()

    @Provides
    fun provideBookmarkTvShowResponseDTOMapper(): BaseMapper<BookmarkTvShowDTO, BookmarkTvShowResponse> =
        BookmarkTvShowResponseDTOMapper()

    @Provides
    fun provideBookmarkTvShowsResponseDTOMapper(): BaseMapper<DataSource.Factory<Int, BookmarkTvShowDTO>, DataSource.Factory<Int, BookmarkTvShowResponse>> =
        BookmarkTvShowsResponseDTOMapper()

    @Provides
    fun provideTvShowBookmarkTvShowMapper(): BaseMapper<TvShow, BookmarkTvShowRequest> =
        TvShowBookmarkTvShowMapper()

    @Provides
    fun provideBookmarkTvShowResponseRequestMapper(): BaseMapper<BookmarkTvShowResponse, BookmarkTvShowRequest> =
        BookmarkTvShowResponseRequestMapper()
}