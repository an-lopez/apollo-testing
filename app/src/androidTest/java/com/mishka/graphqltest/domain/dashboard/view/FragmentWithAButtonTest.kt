package com.mishka.graphqltest.domain.dashboard.view

import androidx.fragment.app.testing.launchFragmentInContainer
import com.mishka.graphqltest.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class FragmentWithAButtonTest{

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testFragmentComponents(){
        launchFragmentInHiltContainer<FragmentWithAButton> {  }

        FragmentWithAButtonTestRobot()
            .checkTextVisibleTrue()
            .clickDisappearButton()
            .checkTextVisibleFalse()
            .clickDisappearButton()
            .checkTextVisibleTrue()
    }


}