package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

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
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Franz Andel on 22/02/21.
 * Android Engineer
 */

class TvShowsFragmentTest {

    private lateinit var bottomNavigation: BottomNavigationView

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        val activityScenario = ActivityScenario.launch(DashboardActivity::class.java)
        activityScenario.onActivity { dashboardActivity ->
            bottomNavigation = dashboardActivity.findViewById(R.id.navView)
            bottomNavigation.selectedItemId = R.id.navigation_tv_shows
        }
        EspressoIdlingResource.decrement()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun checkIfTvShowsShown() {
        val tvShows = TestingUtils.getTvShowsFromJson()
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShows.size - 1
            )
        )
    }

    @Test
    fun checkIfTvShowDetailShown() {
        val tvShowPosition = 0
        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                tvShowPosition,
                click()
            )
        )
        onView(withId(R.id.toolbarDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
    }
}