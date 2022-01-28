package com.mishka.graphqltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.facebook.drawee.backends.pipeline.Fresco
import com.mishka.graphqltest.di.AppModule
import com.mishka.graphqltest.domain.dashboard.DashboardActivity
import com.mishka.graphqltest.domain.dashboard.components.CharacterAdapter.CharacterViewHolder
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class EndToEndTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = activityScenarioRule<DashboardActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        Fresco.initialize(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun addingNewCharacterAndEditing(): Unit = runBlocking{
        onView(withId(R.id.button_add)).perform(click())
        onView(withId(R.id.text_name_holder)).perform(click()).perform(typeText("Juan")).perform(
            closeSoftKeyboard()
        )
        onView(withId(R.id.edit_save)).perform(click())

        Thread.sleep(5000)
        onView(withId(R.id.recycler_main)).perform(
            actionOnItem<CharacterViewHolder>(
                hasDescendant(withText("Juan")), click()
            )
        )
        onView(withId(R.id.text_name_holder)).check(matches(withText("Juan")))
    }
}