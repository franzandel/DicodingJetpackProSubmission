package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.di

import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository.TvShowsRepository
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository.TvShowsRepositoryImpl
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
abstract class TvShowsAbstractModule {

    @Binds
    abstract fun bindTvShowsRepository(tvShowsRepositoryImpl: TvShowsRepositoryImpl): TvShowsRepository
}