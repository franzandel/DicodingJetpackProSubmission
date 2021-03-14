package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.di

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShowsResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.mapper.TvShowsResponseDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.remote.TvShowsNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

/**
 * Created by Franz Andel on 14/03/21.
 * Android Engineer
 */

@Module
@InstallIn(ActivityComponent::class)
object TvShowsModule {

    @Provides
    @ActivityScoped
    fun provideTvShowsNetwork(retrofit: Retrofit): TvShowsNetwork =
        retrofit.create(TvShowsNetwork::class.java)

    @Provides
    fun provideTvShowsResponseDTOMapper(): BaseMapper<TvShowsResponseDTO, List<TvShow>> =
        TvShowsResponseDTOMapper()
}