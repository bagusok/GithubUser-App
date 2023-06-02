package com.bagusok.github.ui.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bagusok.github.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun test() {
        onView(withId(R.id.search)).check(matches(isDisplayed()))
        onView(withId(R.id.search)).perform(click())

    }
}