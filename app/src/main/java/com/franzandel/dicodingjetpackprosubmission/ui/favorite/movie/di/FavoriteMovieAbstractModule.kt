package com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.di

import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.repository.FavoriteMovieRepository
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.repository.FavoriteMovieRepositoryImpl
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
abstract class FavoriteMovieAbstractModule {

    @Binds
    abstract fun bindFavoriteMovieRepository(favoriteMovieRepositoryImpl: FavoriteMovieRepositoryImpl): FavoriteMovieRepository
}