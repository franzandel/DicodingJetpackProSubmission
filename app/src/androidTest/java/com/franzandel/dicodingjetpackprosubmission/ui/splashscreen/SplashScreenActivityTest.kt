package com.franzandel.dicodingjetpackprosubmission.ui.splashscreen

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.instrumentedtest.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Franz Andel on 23/02/21.
 * Android Engineer
 */

class SplashScreenActivityTest {

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        ActivityScenario.launch(SplashScreenActivity::class.java)
    }

    @Test
    fun checkIfNavigatedToDashboard() {
        onView(withId(R.id.layoutDashboard)).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }
}