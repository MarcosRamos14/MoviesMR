package com.marcos.moviesmr.framework.paging

import androidx.paging.PagingSource
import com.marcos.moviesmr.core.data.repository.MoviesRemoteDataSource
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.testing.MainCoroutineRule
import com.marcos.moviesmr.testing.model.MoviesFactory
import com.marcos.moviesmr.testing.model.PagedDataWrapperResponseFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class PopularPagingSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var remoteDataSource: MoviesRemoteDataSource

    private val pagedDataWrapperResponseFactory = PagedDataWrapperResponseFactory()

    private val moviesFactory = MoviesFactory()

    private lateinit var popularPagingSource: PopularPagingSource

    @Before
    fun setUp() {
        popularPagingSource = PopularPagingSource(remoteDataSource)
    }

    @Test
    fun `should return a success load result when load is called`() = runTest {
        // Arrange
        whenever(remoteDataSource.fetchPopular(any()))
            .thenReturn(pagedDataWrapperResponseFactory.create())

        // Act
        val result = popularPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        // Assert
        val expected = listOf(
            moviesFactory.create(MoviesFactory.Popular.Deadpool),
            moviesFactory.create(MoviesFactory.Popular.Furious7)
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = 20
            ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called`() = runTest {
        // Arrange
        val exception = RuntimeException()
        whenever(remoteDataSource.fetchPopular(any()))
            .thenThrow(exception)

        // Act
        val result = popularPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        // Assert
        assertEquals(
            PagingSource.LoadResult.Error<Int, Movie>(exception),
            result
        )
    }
}