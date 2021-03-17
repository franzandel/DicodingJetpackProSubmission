package com.franzandel.dicodingjetpackprosubmission.external.coroutine

import kotlin.coroutines.CoroutineContext

/**
 * Created by Franz Andel on 17/03/21.
 * Android Engineer
 */

interface CoroutineProvider {
    fun main(): CoroutineContext
    fun background(): CoroutineContext
    fun default(): CoroutineContext
    fun testing(): CoroutineContext
}