package com.example.nontonkuy.ui.tvshow.detail

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.nontonkuy.R
import com.example.nontonkuy.ui.MainActivity
import com.example.nontonkuy.ui.tvshow.TvShowListFragment
import com.example.nontonkuy.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TvShowDetailActivityTest {
    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)

        onView(withId(R.id.vp_main))
                .perform(ViewActions.swipeLeft())

        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, ViewActions.click()))

    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun loadRecomended(){
        onView(withId(R.id.rv_recomended_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_recomended_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
    }

    @Test
    fun loadRecomendedDetail(){
        onView(withId(R.id.rv_recomended_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_recomended_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4,ViewActions.click()))
    }
}