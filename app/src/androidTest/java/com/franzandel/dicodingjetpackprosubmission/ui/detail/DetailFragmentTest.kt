//package com.franzandel.dicodingjetpackprosubmission.ui.detail
//
//import androidx.recyclerview.widget.RecyclerView
//import androidx.test.core.app.ActivityScenario
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.contrib.RecyclerViewActions
//import androidx.test.espresso.matcher.ViewMatchers.*
//import com.franzandel.dicodingjetpackprosubmission.R
//import com.franzandel.dicodingjetpackprosubmission.data.HomeCinemaData
//import com.franzandel.dicodingjetpackprosubmission.ui.dashboard.DashboardActivity
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import org.junit.Before
//import org.junit.Test
//
///**
// * Created by Franz Andel on 23/02/21.
// * Android Engineer
// */
//
//class DetailFragmentTest {
//
//    private val movies = HomeCinemaData.getAllMovies()
//    private val tvShows = HomeCinemaData.getAllTvShows()
//    private lateinit var bottomNavigation: BottomNavigationView
//    private lateinit var activityScenario: ActivityScenario<DashboardActivity>
//
//    @Before
//    fun setup() {
//        activityScenario = ActivityScenario.launch(DashboardActivity::class.java)
//    }
//
//    @Test
//    fun checkIfMovieDetailDataCorrectlyShown() {
//        val moviePosition = 0
//        onView(withId(R.id.rvMovies)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                moviePosition,
//                ViewActions.click()
//            )
//        )
//        onView(withId(R.id.toolbarDetail)).check(matches(isDisplayed()))
//        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
//        onView(withId(R.id.app_bar)).perform(ViewActions.swipeUp())
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
//    }
//
//    @Test
//    fun checkIfTvShowDetailDataCorrectlyShown() {
//        activityScenario.onActivity { dashboardActivity ->
//            bottomNavigation = dashboardActivity.findViewById(R.id.navView)
//            bottomNavigation.selectedItemId = R.id.navigation_tv_shows
//        }
//
//        val tvShowPosition = 0
//        onView(withId(R.id.rvTvShows)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                tvShowPosition,
//                ViewActions.click()
//            )
//        )
//        onView(withId(R.id.toolbarDetail)).check(matches(isDisplayed()))
//        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
//        onView(withId(R.id.app_bar)).perform(ViewActions.swipeUp())
//        onView(withId(R.id.tvRelease)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvRelease)).check(matches(withText(tvShows[tvShowPosition].releaseYear)))
//        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvGenre)).check(matches(withText(tvShows[tvShowPosition].genre)))
//        onView(withId(R.id.tvLength)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvLength)).check(matches(withText(tvShows[tvShowPosition].length)))
//        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvRating)).check(matches(withText(tvShows[tvShowPosition].rating)))
//        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
//        onView(withId(R.id.tvOverview)).check(matches(withText(tvShows[tvShowPosition].description)))
//        onView(withId(R.id.rvDetail)).check(matches(isDisplayed()))
//    }
//}