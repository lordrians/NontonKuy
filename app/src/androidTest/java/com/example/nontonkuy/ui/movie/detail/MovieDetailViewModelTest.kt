package com.example.nontonkuy.ui.movie.detail

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.nontonkuy.R
import com.example.nontonkuy.ui.MainActivity
import com.example.nontonkuy.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailViewModelTest {

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.rv_movie))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4,ViewActions.click()))

    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun loadRecomendation(){
        onView(withId(R.id.rv_recomended_mov))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
    }


    @Test
    fun loadDetailRecomendation(){
        onView(withId(R.id.rv_recomended_mov))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(19,ViewActions.click()))
        pressBack()
        pressBack()
    }

}