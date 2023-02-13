package com.marcos.moviesmr.presentation.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marcos.moviesmr.R
import com.marcos.moviesmr.framework.di.BaseUrlModule
import com.marcos.moviesmr.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@UninstallModules(BaseUrlModule::class)
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<HomeFragment>()
    }

    @Test
    fun shouldShowPopularMovies_viewIsCreated() {
        onView(
            withId(R.id.recycler_movies_home)
        ).check(
            matches(isDisplayed())
        )
    }
}