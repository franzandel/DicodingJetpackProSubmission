package com.franzandel.dicodingjetpackprosubmission.ui.movies.di

import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository.MoviesRepository
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by Franz Andel on 14/03/21.
 * Android Engineer
 */

@Module
@InstallIn(ActivityComponent::class)
abstract class MoviesAbstractModule {

    @Binds
    abstract fun bindMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}