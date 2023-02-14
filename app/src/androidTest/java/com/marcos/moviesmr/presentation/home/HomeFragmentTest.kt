package com.marcos.moviesmr.presentation.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marcos.moviesmr.R
import com.marcos.moviesmr.extension.asJsonString
import com.marcos.moviesmr.framework.di.BaseUrlModule
import com.marcos.moviesmr.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
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

    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer().apply {
            start(8080)
        }
        launchFragmentInHiltContainer<HomeFragment>()
    }

    @Test
    fun shouldShowPopularMovies_viewIsCreated() {
        // Arrange
        server.enqueue(MockResponse().setBody("movies_p1.json".asJsonString()))

        // Assert
        onView(
            withId(R.id.recycler_movies_home)
        ).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun shouldLoadMorePopularMovies_whenNewPageIsRequested() {
        // Arrange
        server.enqueue(MockResponse().setBody("movies_p1.json".asJsonString()))
        server.enqueue(MockResponse().setBody("movies_p2.json".asJsonString()))

        // Action
        onView(
            withId(R.id.recycler_movies_home)
        ).perform(
            RecyclerViewActions.scrollToPosition<HomeViewHolder>(20)
        )

        // Assert
        onView(
            withText("Beneath the Surface")
        ).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun shouldShowErrorView_whenReceivesAnErrorFromApi() {
        // Arrange
        server.enqueue(MockResponse().setResponseCode(404))

        // Assert
        onView(
            withId(R.id.text_initial_loading_error)
        ).check(
            matches(isDisplayed())
        )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}