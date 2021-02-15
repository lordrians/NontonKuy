package com.example.nontonkuy.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.nontonkuy.R
import com.example.nontonkuy.ui.movie.main.MovieListFragment
import com.example.nontonkuy.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun loadMovie(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        2
                )
        )
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3,click())
        )
        onView(withId(R.id.iv_movdetail_banner)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_movdetail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_budget)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_revenue)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_desc)).check(matches(isDisplayed()))

    }

    @Test
    fun loadTvShow(){
        onView(withId(R.id.vp_main))
                .check(matches(isDisplayed()))
                .perform(swipeLeft())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3)
        )
    }

    @Test
    fun loadDetailTvShow(){
        onView(withId(R.id.vp_main))
                .check(matches(isDisplayed()))
                .perform(swipeLeft())
        Thread.sleep(1000)
        onView(withId(R.id.rv_tvshow))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        onView(withId(R.id.iv_tvshowdetail_banner)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_tvshowdetail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_season_eps)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_recomended_tvshow)).check(matches(isDisplayed()))
    }

    @Test
    fun checkUncheckFavoriteMovie(){
        onView(withId(R.id.rv_movie))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.iv_movdetail_banner)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_movdetail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_budget)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_revenue)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movdetail_desc)).check(matches(isDisplayed()))

        Thread.sleep(1000)

        onView(withId(R.id.action_favorite))
                .check(matches(isDisplayed()))
                .perform(click())

        pressBack()

        onView(withId(R.id.action_favorite))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(withId(R.id.rv_fav_movie))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.action_favorite))
                .check(matches(isDisplayed()))
                .perform(click())

        pressBack()
    }

    @Test
    fun checkUncheckTvShow(){
        onView(withId(R.id.vp_main))
                .check(matches(isDisplayed()))
                .perform(swipeLeft())
        Thread.sleep(1000)
        onView(withId(R.id.rv_tvshow))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        onView(withId(R.id.iv_tvshowdetail_banner)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_tvshowdetail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_season_eps)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_recomended_tvshow)).check(matches(isDisplayed()))

        Thread.sleep(1000)

        onView(withId(R.id.action_favorite))
                .check(matches(isDisplayed()))
                .perform(click())

        pressBack()

        onView(withId(R.id.action_favorite))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(withId(R.id.vp_favorite))
                .check(matches(isDisplayed()))
                .perform(swipeLeft())

        Thread.sleep(1000)
        onView(withId(R.id.rv_fav_tvshow))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.action_favorite))
                .check(matches(isDisplayed()))
                .perform(click())

        pressBack()
    }
}