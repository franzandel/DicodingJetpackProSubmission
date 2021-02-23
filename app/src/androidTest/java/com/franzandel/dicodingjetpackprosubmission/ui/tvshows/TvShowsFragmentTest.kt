package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.HomeCinemaData
import com.franzandel.dicodingjetpackprosubmission.ui.dashboard.DashboardActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.junit.Before
import org.junit.Test

/**
 * Created by Franz Andel on 22/02/21.
 * Android Engineer
 */

class TvShowsFragmentTest {

    private val tvShows = HomeCinemaData.getAllTvShows()
    private lateinit var bottomNavigation: BottomNavigationView

    @Before
    fun setup() {
        val activityScenario = ActivityScenario.launch(DashboardActivity::class.java)
        activityScenario.onActivity { dashboardActivity ->
            bottomNavigation = dashboardActivity.findViewById(R.id.navView)
            bottomNavigation.selectedItemId = R.id.navigation_tv_shows
        }
    }

    @Test
    fun checkIfTvShowsShown() {
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShows.size
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
        onView(withId(R.id.app_bar)).perform(ViewActions.swipeUp())
        onView(withId(R.id.tvRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRelease)).check(matches(withText(tvShows[tvShowPosition].releaseYear)))
        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText(tvShows[tvShowPosition].genre)))
        onView(withId(R.id.tvLength)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLength)).check(matches(withText(tvShows[tvShowPosition].length)))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(withText(tvShows[tvShowPosition].rating)))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(tvShows[tvShowPosition].description)))
        onView(withId(R.id.rvDetail)).check(matches(isDisplayed()))
    }
}