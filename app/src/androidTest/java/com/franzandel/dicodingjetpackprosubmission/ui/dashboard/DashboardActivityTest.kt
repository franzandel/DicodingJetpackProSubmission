package com.franzandel.dicodingjetpackprosubmission.ui.dashboard

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.franzandel.dicodingjetpackprosubmission.R
import org.junit.Before
import org.junit.Test

/**
 * Created by Franz Andel on 20/02/21.
 * Android Engineer
 */

class DashboardActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(DashboardActivity::class.java)
    }

    @Test
    fun checkIfBottomNavShown() {
        onView(withId(R.id.navView)).check(matches(isDisplayed()))
    }
}