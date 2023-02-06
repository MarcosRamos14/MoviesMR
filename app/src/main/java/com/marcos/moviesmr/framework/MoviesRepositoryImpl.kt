package com.marcos.moviesmr.framework

import androidx.paging.PagingSource
import com.marcos.moviesmr.core.data.repository.MoviesRemoteDataSource
import com.marcos.moviesmr.core.data.repository.MoviesRepository
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.framework.paging.PopularPagingSource
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override fun getPopular(query: String) : PagingSource<Int, Movie> {
        return PopularPagingSource(remoteDataSource)
    }

    override suspend fun getSimilar(movieId: Int): List<Movie> {
        return remoteDataSource.fetchSimilar(movieId)
    }
}