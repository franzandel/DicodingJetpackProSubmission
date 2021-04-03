package com.franzandel.dicodingjetpackprosubmission.ui.bookmark

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.franzandel.dicodingjetpackprosubmission.R
import org.junit.Before
import org.junit.Test

/**
 * Created by Franz Andel on 03/04/21.
 * Android Engineer
 */

class BookmarkActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(BookmarkActivity::class.java)
    }

    @Test
    fun checkIfBookmarkActivityShown() {
        Espresso.onView(withId(R.id.ablBookmark)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.mtbBookmark)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))
    }
}