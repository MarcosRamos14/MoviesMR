package com.marcos.moviesmr.presentation.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Before
    fun setUp() {
        launchFragmentInContainer<HomeFragment>()
    }

    @Test
    fun shouldShowPopularMovies_viewIsCreated() {

    }
}