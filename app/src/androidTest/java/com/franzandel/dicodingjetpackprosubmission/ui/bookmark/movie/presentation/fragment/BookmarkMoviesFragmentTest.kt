package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.BookmarkActivity
import com.franzandel.dicodingjetpackprosubmission.ui.custom.ClickRecyclerViewChildView
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Test

/**
 * Created by Franz Andel on 03/04/21.
 * Android Engineer
 */

class BookmarkMoviesFragmentTest {

    companion object {
        private const val BOOKMARK_MOVIE_POSITION = 0
    }

    private lateinit var bookmarkMoviesFragment: BookmarkMoviesFragment

    @Before
    fun setup() {
        val activityScenario = ActivityScenario.launch(BookmarkActivity::class.java)
        activityScenario.onActivity { bookmarkActivity ->
            bookmarkMoviesFragment = bookmarkActivity.bookmarkMoviesFragment
        }
    }

    @Test
    fun checkIfBookmarkMoviesShown() {
        onView(withId(R.id.rvBookmarkMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvBookmarkMovies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                bookmarkMoviesFragment.bookmarkMoviesSizeForUITesting - 1
            )
        )
    }

    @Test
    fun checkIfDeleteButtonWorks() {
        if (bookmarkMoviesFragment.bookmarkMoviesSizeForUITesting > 0) {
            onView(withId(R.id.rvBookmarkMovies)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    BOOKMARK_MOVIE_POSITION,
                    ClickRecyclerViewChildView.click(R.id.ivDeleteBookmarkMovie)
                )
            )

            onView(withId(android.R.id.button1)).perform(click())
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.bookmark_deleted)))
        }
    }

    @Test
    fun checkIfDeleteButtonWorksThenUndo() {
        if (bookmarkMoviesFragment.bookmarkMoviesSizeForUITesting > 0) {
            onView(withId(R.id.rvBookmarkMovies)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    BOOKMARK_MOVIE_POSITION,
                    ClickRecyclerViewChildView.click(R.id.ivDeleteBookmarkMovie)
                )
            )

            onView(withId(android.R.id.button1)).perform(click())
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.bookmark_deleted)))
            onView(allOf(withId(com.google.android.material.R.id.snackbar_action)))
                .perform(click())
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.bookmark_deleted_undo_success)))
        }
    }

    @Test
    fun checkIfDeleteButtonWorksThenCancel() {
        if (bookmarkMoviesFragment.bookmarkMoviesSizeForUITesting > 0) {
            onView(withId(R.id.rvBookmarkMovies)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    BOOKMARK_MOVIE_POSITION,
                    ClickRecyclerViewChildView.click(R.id.ivDeleteBookmarkMovie)
                )
            )

            onView(withId(android.R.id.button2)).perform(click())
        }
    }
}