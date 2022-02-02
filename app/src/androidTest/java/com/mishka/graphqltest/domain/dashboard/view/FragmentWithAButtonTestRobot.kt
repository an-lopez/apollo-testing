package com.mishka.graphqltest.domain.dashboard.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.mishka.graphqltest.R
import org.hamcrest.Matchers.not

class FragmentWithAButtonTestRobot {

    fun clickDisappearButton(): FragmentWithAButtonTestRobot {
        onView(withId(R.id.button_disappear)).perform(ViewActions.click())
        return this
    }

    fun checkTextVisibleTrue(): FragmentWithAButtonTestRobot {
        onView(withId(R.id.text_common_text))
            .check(matches(isDisplayed()))
        return this
    }

    fun checkTextVisibleFalse(): FragmentWithAButtonTestRobot {
        onView(withId(R.id.text_common_text))
            .check(matches(not(isDisplayed())))
        return this
    }

}