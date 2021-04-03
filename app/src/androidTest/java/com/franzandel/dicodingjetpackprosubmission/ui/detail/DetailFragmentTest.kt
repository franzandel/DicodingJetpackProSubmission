package com.franzandel.dicodingjetpackprosubmission.ui.detail

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.instrumentedtest.EspressoIdlingResource
import com.franzandel.dicodingjetpackprosubmission.ui.dashboard.DashboardActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Franz Andel on 23/02/21.
 * Android Engineer
 */

class DetailFragmentTest {

    companion object {
        private const val MOVIE_POSITION = 0
        private const val TV_SHOW_POSITION = 0
    }

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fabBookmark: FloatingActionButton
    private lateinit var activityScenario: ActivityScenario<DashboardActivity>

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        activityScenario = ActivityScenario.launch(DashboardActivity::class.java)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun checkIfMovieDetailDataCorrectlyShown() {
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                MOVIE_POSITION,
                click()
            )
        )
        onView(withId(R.id.toolbarDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar)).perform(ViewActions.swipeUp())
        onView(withId(R.id.tvRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLength)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.rvDetail)).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfMovieDetailBookmarkWork() {
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                MOVIE_POSITION,
                click()
            )
        )
        activityScenario.onActivity { dashboardActivity ->
            fabBookmark = dashboardActivity.findViewById(R.id.fabBookmark)
        }

        val snackbarMessage = if (fabBookmark.isSelected) {
            R.string.detail_bookmark_removed
        } else {
            R.string.detail_bookmark_added
        }
        onView(withId(R.id.fabBookmark)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(snackbarMessage)))

        if (fabBookmark.isSelected) {
            onView(allOf(withId(com.google.android.material.R.id.snackbar_action))).perform(click())
            onView(withId(R.id.bookmarkActivity)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun checkIfTvShowDetailDataCorrectlyShown() {
        activityScenario.onActivity { dashboardActivity ->
            bottomNavigation = dashboardActivity.findViewById(R.id.navView)
            bottomNavigation.selectedItemId = R.id.navigation_tv_shows
        }
        EspressoIdlingResource.decrement()

        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                TV_SHOW_POSITION,
                click()
            )
        )
        onView(withId(R.id.toolbarDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar)).perform(ViewActions.swipeUp())
        onView(withId(R.id.tvRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLength)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.rvDetail)).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfTvShowDetailBookmarkWork() {
        activityScenario.onActivity { dashboardActivity ->
            bottomNavigation = dashboardActivity.findViewById(R.id.navView)
            bottomNavigation.selectedItemId = R.id.navigation_tv_shows
        }
        EspressoIdlingResource.decrement()

        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                TV_SHOW_POSITION,
                click()
            )
        )

        activityScenario.onActivity { dashboardActivity ->
            fabBookmark = dashboardActivity.findViewById(R.id.fabBookmark)
        }

        val snackbarMessage = if (fabBookmark.isSelected)
            R.string.detail_bookmark_removed
        else
            R.string.detail_bookmark_added

        onView(withId(R.id.fabBookmark)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(snackbarMessage)))

        if (fabBookmark.isSelected) {
            onView(allOf(withId(com.google.android.material.R.id.snackbar_action))).perform(click())
            onView(withId(R.id.bookmarkActivity)).check(matches(isDisplayed()))
        }
    }
}