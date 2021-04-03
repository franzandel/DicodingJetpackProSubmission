package com.franzandel.dicodingjetpackprosubmission.ui.custom

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher


/**
 * Created by Franz Andel on 03/04/21.
 * Android Engineer
 */

object ClickRecyclerViewChildView {
    fun click(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? = null

            override fun getDescription(): String =
                "Click on a child view with specified id."

            override fun perform(uiController: UiController?, view: View) {
                val childView: View = view.findViewById(id)
                childView.performClick()
            }
        }
    }
}