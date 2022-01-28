package com.mishka.graphqltest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.mishka.graphqltest.R
import com.mishka.graphqltest.di.AppModule
import com.mishka.graphqltest.domain.dashboard.view.CharactersDashboardFragment
import com.mishka.graphqltest.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class CharacterDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun `testLaunchFragmentInHiltContainer`(){
        val navController = mockk<NavController>(relaxed = true)
        launchFragmentInHiltContainer<CharactersDashboardFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.button_add)).perform(click())
        verify {
            navController.navigate(R.id.characterDetailFragment)
        }
    }



}