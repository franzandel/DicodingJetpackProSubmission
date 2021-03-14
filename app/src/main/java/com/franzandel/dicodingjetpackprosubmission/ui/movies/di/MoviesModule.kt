package com.franzandel.dicodingjetpackprosubmission.ui.movies.di

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MoviesResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.mapper.MoviesResponseDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.remote.MoviesNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

/**
 * Created by Franz Andel on 13/03/21.
 * Android Engineer
 */

@Module
@InstallIn(ActivityComponent::class)
object MoviesModule {

    @Provides
    @ActivityScoped
    fun provideMoviesNetwork(retrofit: Retrofit): MoviesNetwork =
        retrofit.create(MoviesNetwork::class.java)

    @Provides
    fun provideMoviesResponseDTOMapper(): BaseMapper<MoviesResponseDTO, List<Movie>> =
        MoviesResponseDTOMapper()
}