package com.franzandel.dicodingjetpackprosubmission.instrumentedtest

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Created by Franz Andel on 23/02/21.
 * Android Engineer
 */

object EspressoIdlingResource {

    private const val HOME_CINEMA = "home_cinema"
    val countingIdlingResource = CountingIdlingResource(HOME_CINEMA)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}