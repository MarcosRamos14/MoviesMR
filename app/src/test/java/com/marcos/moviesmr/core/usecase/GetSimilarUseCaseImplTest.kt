package com.marcos.moviesmr.core.usecase

import com.marcos.moviesmr.core.data.repository.MoviesRepository
import com.marcos.moviesmr.core.usecase.base.ResultStatus
import com.marcos.moviesmr.testing.MainCoroutineRule
import com.marcos.moviesmr.testing.model.MoviesFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GetSimilarUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: MoviesRepository

    private val movie = MoviesFactory().create(MoviesFactory.Popular.Deadpool)

    private val similar = listOf(MoviesFactory().create(MoviesFactory.Popular.Deadpool))

    private lateinit var getSimilarUseCase: GetSimilarUseCase

    @Before
    fun setUp() {
        getSimilarUseCase = GetSimilarUseCaseImpl(repository)
    }

    @Test
    fun `should return Success from ResultStatus when getSimilar return success`() = runTest {
        // Arrange
        whenever(repository.getSimilar(movie.id ?: 0))
            .thenReturn(similar)

        // Act
        val result = getSimilarUseCase.invoke(
            GetSimilarUseCase.GetSimilarParams(movie.id ?: 0)
        )

        // Assert
        val resultList = result.toList()

        assertEquals(ResultStatus.Loading, resultList[0])
    }
}