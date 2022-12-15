package com.marcos.moviesmr.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marcos.moviesmr.core.data.repository.PopularRemoteDataSource
import com.marcos.moviesmr.core.domain.model.Popular
import com.marcos.moviesmr.framework.network.response.toMoviesModel

class PopularPagingSource(
    private val remoteDataSource: PopularRemoteDataSource,
    private val query: String
): PagingSource<Int, Popular>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Popular> {
        return try {
            val page = params.key ?: 1

            val queries = hashMapOf(
                "page" to page.toString()
            )

            if (query.isNotEmpty()) {
                queries["page"] = query
            }

            val response = remoteDataSource.fetchPopular(queries)

            val responsePage = response.page
            val totalPopular =response.total

            LoadResult.Page(
                data = response.results.map { it.toMoviesModel() },
                prevKey = null,
                nextKey = if (responsePage < totalPopular) {
                        responsePage + LIMIT
                } else null
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Popular>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}