package com.franzandel.dicodingjetpackprosubmission.ui.movies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.instrumentedtest.EspressoIdlingResource
import com.franzandel.dicodingjetpackprosubmission.ui.dashboard.DashboardActivity
import com.franzandel.dicodingjetpackprosubmission.utils.TestingUtils
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Franz Andel on 20/02/21.
 * Android Engineer
 */

class MoviesFragmentTest {

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        ActivityScenario.launch(DashboardActivity::class.java)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun checkIfMoviesShown() {
        val movies = TestingUtils.getMoviesFromJson()
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movies.size - 1
            )
        )
    }

    @Test
    fun checkIfMovieDetailShown() {
        val moviePosition = 0
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                moviePosition,
                click()
            )
        )
        onView(withId(R.id.toolbarDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
    }
}