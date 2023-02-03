package com.marcos.moviesmr.core.usecase

import androidx.paging.PagingConfig
import com.marcos.moviesmr.core.data.repository.PopularRepository
import com.marcos.moviesmr.testing.MainCoroutineRule
import com.marcos.moviesmr.testing.model.MoviesFactory
import com.marcos.moviesmr.testing.model.PagingSourceFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class GetPopularUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: PopularRepository

    private lateinit var getPopularUseCase: GetPopularUseCase

    private val moviesFactory = MoviesFactory().create(MoviesFactory.Popular.Deadpool)

    private val pagingSourceFactory = PagingSourceFactory().create(listOf(moviesFactory))

    @Before
    fun setUp() {
        getPopularUseCase = GetPopularUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            // Arrange
            whenever(repository.getPopular(""))
                .thenReturn(pagingSourceFactory)

            // Act
            val result = getPopularUseCase.invoke(
                GetPopularUseCase.GetPopularParams("", PagingConfig(20))
            )

            // Assert
            verify(repository).getPopular("")
            assertNotNull(result.first())
        }
}