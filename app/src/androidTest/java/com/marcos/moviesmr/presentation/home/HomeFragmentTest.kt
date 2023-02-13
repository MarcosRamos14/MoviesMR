package com.marcos.moviesmr.presentation.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marcos.moviesmr.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
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

    }
}