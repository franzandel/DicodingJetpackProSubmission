package com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.di

import androidx.lifecycle.LiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.data.local.FavoriteDatabase
import com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.mapper.MovieFavoriteMovieMapper
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.dao.FavoriteMovieDao
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieDTO
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.mapper.FavoriteMovieRequestMapper
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.mapper.FavoriteMoviesResponseDTOMapper
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
object FavoriteMovieModule {

    @Provides
    @ActivityScoped
    fun provideFavoriteMovieDao(favoriteDatabase: FavoriteDatabase): FavoriteMovieDao =
        favoriteDatabase.favoriteMovieDao()

    @Provides
    fun provideFavoriteMovieRequestMapper(): BaseMapper<FavoriteMovieRequest, FavoriteMovieDTO> =
        FavoriteMovieRequestMapper()

    @Provides
    fun provideFavoriteMoviesResponseDTOMapper(): BaseMapper<LiveData<List<FavoriteMovieDTO>>, LiveData<List<FavoriteMovieResponse>>> =
        FavoriteMoviesResponseDTOMapper()

    @Provides
    fun provideMovieFavoriteMovieMapper(): BaseMapper<Movie, FavoriteMovieRequest> =
        MovieFavoriteMovieMapper()
}