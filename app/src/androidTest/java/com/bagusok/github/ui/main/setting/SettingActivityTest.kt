package com.bagusok.github.ui.main.setting

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bagusok.github.R
import com.bagusok.github.ui.main.MainActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SettingActivityTest {
    @Before
    fun setup(){
        ActivityScenario.launch(SettingActivity::class.java)
    }

    @Test
    fun test() {
        Espresso.onView(ViewMatchers.withId(R.id.tg_dark))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tg_dark)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tg_dark))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tg_dark)).perform(ViewActions.click())

    }
}