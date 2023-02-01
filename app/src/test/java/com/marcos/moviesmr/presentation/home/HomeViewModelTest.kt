package com.marcos.moviesmr.presentation.home

import androidx.paging.PagingData
import com.marcos.moviesmr.core.usecase.GetPopularUseCase
import com.marcos.moviesmr.testing.MainCoroutineRule
import com.marcos.moviesmr.testing.model.MoviesFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getPopularUseCase: GetPopularUseCase

    private val moviesFactory = MoviesFactory()

    private val pagingDataMovies = PagingData.from(
        listOf(
            moviesFactory.create(MoviesFactory.Popular.Deadpool),
            moviesFactory.create(MoviesFactory.Popular.Furious7)
        )
    )

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(getPopularUseCase)
    }

    @Test
    fun `should validate the paging data object values when calling popularPagingData`() =
        runTest {
            // Arrange
            whenever(getPopularUseCase.invoke(any()))
                .thenReturn(
                    flowOf(pagingDataMovies)
                )

            // Act
            val result = homeViewModel.popularPagingData("")

            // Assert
            assertNotNull(result.first())
        }

    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the use case returns an exception`() =
        runTest {
            whenever(getPopularUseCase.invoke(any()))
                .thenThrow(RuntimeException())

            homeViewModel.popularPagingData("")
        }
}