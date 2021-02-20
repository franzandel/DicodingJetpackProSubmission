package com.franzandel.dicodingjetpackprosubmission.ui.movies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.franzandel.dicodingjetpackprosubmission.DashboardActivity
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.HomeCinemaData
import org.junit.Before
import org.junit.Test

/**
 * Created by Franz Andel on 20/02/21.
 * Android Engineer
 */

class MoviesFragmentTest {

    private val movies = HomeCinemaData.getAllMovies()

    @Before
    fun setup() {
        ActivityScenario.launch(DashboardActivity::class.java)
    }

    @Test
    fun checkIfMoviesShown() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movies.size
            )
        )
    }

    @Test
    fun clickMovie() {
        val moviePosition = 0
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                moviePosition,
                click()
            )
        )
        onView(withId(R.id.toolbarDetail)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvRelease)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvRelease)).check(matches(withText(movies[moviePosition].releaseDate)))
//        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvGenre)).check(matches(withText(movies[moviePosition].genre)))
//        onView(withId(R.id.tvLength)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvLength)).check(matches(withText(movies[moviePosition].length)))
//        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvRating)).check(matches(withText(movies[moviePosition].rating)))
//        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvOverview)).check(matches(withText(movies[moviePosition].description)))
//        onView(withId(R.id.rvDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
    }
}