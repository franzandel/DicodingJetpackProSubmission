package com.franzandel.dicodingjetpackprosubmission.external.coroutine

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class CoroutineProviderImpl @Inject constructor() :
    CoroutineProvider {

    override fun main(): CoroutineContext = Dispatchers.Main

    override fun background(): CoroutineContext = Dispatchers.IO

    override fun default(): CoroutineContext = Dispatchers.Default

    override fun testing(): CoroutineContext = Dispatchers.Unconfined
}