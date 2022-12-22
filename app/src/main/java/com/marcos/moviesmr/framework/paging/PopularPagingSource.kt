package com.marcos.moviesmr.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marcos.moviesmr.core.data.repository.PopularRemoteDataSource
import com.marcos.moviesmr.core.domain.model.Popular
import com.marcos.moviesmr.framework.network.response.toMoviesModel

class PopularPagingSource(
    private val remoteDataSource: PopularRemoteDataSource
) : PagingSource<Int, Popular>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>) : LoadResult<Int, Popular> {
        return try {
            val page = params.key ?: 1
            val queries = hashMapOf("page" to page.toString())
            val response = remoteDataSource.fetchPopular(queries)
            val responsePage = response.page ?: 0
            val totalPopular =response.total ?: 0

            LoadResult.Page(
                data = response.results?.map { it.toMoviesModel() } ?: emptyList(),
                prevKey = null,
                nextKey = if (responsePage < totalPopular) {
                        responsePage + LIMIT
                } else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Popular>) : Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}