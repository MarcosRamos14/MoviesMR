package com.marcos.moviesmr.testing.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marcos.moviesmr.core.domain.model.Movie

class PagingSourceFactory {

    fun create(movies: List<Movie>) = object : PagingSource<Int, Movie>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
            return LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = 20
            )
        }

        override fun getRefreshKey(state: PagingState<Int, Movie>) = 1

    }
}