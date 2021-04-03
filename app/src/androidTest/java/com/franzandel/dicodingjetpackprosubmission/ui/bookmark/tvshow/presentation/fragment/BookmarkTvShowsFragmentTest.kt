package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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

class BookmarkTvShowsFragmentTest {

    companion object {
        private const val BOOKMARK_TV_SHOW_POSITION = 0
    }

    private lateinit var bookmarkTvShowsFragment: BookmarkTvShowsFragment

    @Before
    fun setup() {
        val activityScenario = ActivityScenario.launch(BookmarkActivity::class.java)
        activityScenario.onActivity { bookmarkActivity ->
            bookmarkTvShowsFragment = bookmarkActivity.bookmarkTvShowsFragment
//            val asdf = bookmarkActivity.findViewById<ViewPager>(R.id.viewPager)
//            onView(withId(R.id.viewPager)).perform(swipeRight())
//            onView(withId(R.id.viewPager)).perform(ViewPagerActions.scrollRight())
        }
        onView(withId(R.id.viewPager)).perform(ViewPagerActions.scrollRight())
    }

    @Test
    fun checkIfBookmarkTvShowsShown() {
        onView(withId(R.id.rvBookmarkTvShows))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rvBookmarkTvShows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                bookmarkTvShowsFragment.bookmarkTvShowsSizeForUITesting - 1
            )
        )
    }

    @Test
    fun checkIfDeleteButtonWorks() {
        if (bookmarkTvShowsFragment.bookmarkTvShowsSizeForUITesting > 0) {
            onView(withId(R.id.rvBookmarkTvShows)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    BOOKMARK_TV_SHOW_POSITION,
                    ClickRecyclerViewChildView.click(R.id.ivDeleteBookmarkTvShow)
                )
            )

            onView(withId(android.R.id.button1)).perform(click())
            onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.bookmark_deleted)))
        }
    }

    @Test
    fun checkIfDeleteButtonWorksThenUndo() {
        if (bookmarkTvShowsFragment.bookmarkTvShowsSizeForUITesting > 0) {
            onView(withId(R.id.rvBookmarkTvShows)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    BOOKMARK_TV_SHOW_POSITION,
                    ClickRecyclerViewChildView.click(R.id.ivDeleteBookmarkTvShow)
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
        if (bookmarkTvShowsFragment.bookmarkTvShowsSizeForUITesting > 0) {
            onView(withId(R.id.rvBookmarkTvShows)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    BOOKMARK_TV_SHOW_POSITION,
                    ClickRecyclerViewChildView.click(R.id.ivDeleteBookmarkTvShow)
                )
            )

            onView(withId(android.R.id.button2)).perform(click())
        }
    }
}