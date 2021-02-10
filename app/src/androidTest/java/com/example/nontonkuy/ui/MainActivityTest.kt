package com.example.nontonkuy.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.nontonkuy.R
import com.example.nontonkuy.ui.movie.main.MovieListFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
        FragmentScenario.launch(MovieListFragment::class.java)
    }

    @Test
    fun cekAja(){
        onView(withId(R.id.vp_main)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

    }

}