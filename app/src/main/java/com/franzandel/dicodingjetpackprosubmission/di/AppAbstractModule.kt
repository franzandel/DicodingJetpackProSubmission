package com.franzandel.dicodingjetpackprosubmission.di

import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Franz Andel on 17/03/21.
 * Android Engineer
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class AppAbstractModule {

    @Binds
    abstract fun bindCoroutineProvider(coroutineProviderImpl: CoroutineProviderImpl): CoroutineProvider
}