package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.di

import androidx.paging.DataSource
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.data.local.BookmarkDatabase
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.dao.BookmarkMovieDao
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.mapper.BookmarkMovieRequestDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.mapper.BookmarkMovieResponseDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.mapper.BookmarkMoviesResponseDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.mapper.BookmarkMovieResponseRequestMapper
import com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.mapper.MovieBookmarkMovieMapper
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@Module
@InstallIn(ActivityComponent::class)
object BookmarkMovieModule {

    @Provides
    @ActivityScoped
    fun provideBookmarkMovieDao(bookmarkDatabase: BookmarkDatabase): BookmarkMovieDao =
        bookmarkDatabase.bookmarkMovieDao()

    @Provides
    fun provideBookmarkMovieRequestDTOMapper(): BaseMapper<BookmarkMovieRequest, BookmarkMovieDTO> =
        BookmarkMovieRequestDTOMapper()

    @Provides
    fun provideBookmarkMovieResponseDTOMapper(): BaseMapper<BookmarkMovieDTO, BookmarkMovieResponse> =
        BookmarkMovieResponseDTOMapper()

    @Provides
    fun provideBookmarkMoviesResponseDTOMapper(): BaseMapper<DataSource.Factory<Int, BookmarkMovieDTO>, DataSource.Factory<Int, BookmarkMovieResponse>> =
        BookmarkMoviesResponseDTOMapper()

    @Provides
    fun provideMovieBookmarkMovieMapper(): BaseMapper<Movie, BookmarkMovieRequest> =
        MovieBookmarkMovieMapper()

    @Provides
    fun provideBookmarkMovieResponseRequestMapper(): BaseMapper<BookmarkMovieResponse, BookmarkMovieRequest> =
        BookmarkMovieResponseRequestMapper()
}