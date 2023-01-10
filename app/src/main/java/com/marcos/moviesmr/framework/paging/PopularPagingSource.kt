package com.marcos.moviesmr.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marcos.moviesmr.core.data.repository.PopularRemoteDataSource
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.framework.network.response.toMovieModel

class PopularPagingSource(
    private val remoteDataSource: PopularRemoteDataSource
) : PagingSource<Int, Movie>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>) : LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val queries = hashMapOf("page" to page.toString())
            val response = remoteDataSource.fetchPopular(queries)
            val responsePage = response.page ?: 0
            val totalPopular =response.total ?: 0

            LoadResult.Page(
                data = response.results?.map { it.toMovieModel() } ?: emptyList(),
                prevKey = null,
                nextKey = if (responsePage < totalPopular) {
                        responsePage + LIMIT
                } else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>) : Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}