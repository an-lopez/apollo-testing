package com.mishka.graphqltest.domain.dashboard.view

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.platform.util.TestOutputEmitter.takeScreenshot
import com.facebook.drawee.backends.pipeline.Fresco
import com.mishka.graphqltest.R
import com.mishka.graphqltest.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CharacterDetailFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        Fresco.initialize(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun checkThatMaleIsSelected() {
        //launchFragmentInContainer<CharacterDetailFragment>(null)

        launchFragmentInHiltContainer<CharacterDetailFragment> {
        }

        onView(withId(R.id.text_name_holder)).perform(click()).perform(typeText("Juan Perez"))
        closeSoftKeyboard()
        onView(withId(R.id.text_gender_holder)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Femenino")))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.text_gender_holder)).check(matches(withText("Femenino")))

        onView(withId(R.id.text_image_holder)).perform(click()).perform(typeText("Juan Perez"))
        closeSoftKeyboard()

        takeScreenshot("options")
    }
}