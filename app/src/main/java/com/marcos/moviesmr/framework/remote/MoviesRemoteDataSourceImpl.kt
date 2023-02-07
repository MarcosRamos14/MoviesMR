package com.marcos.moviesmr.framework.remote

import com.marcos.moviesmr.core.data.repository.MoviesRemoteDataSource
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.framework.network.MoviesApi
import com.marcos.moviesmr.framework.network.response.MovieResponse
import com.marcos.moviesmr.framework.network.response.PagedDataWrapperResponse
import com.marcos.moviesmr.framework.network.response.toMovieModel
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesRemoteDataSource {

    override suspend fun fetchPopular(
        queries: Map<String, String>
    ) : PagedDataWrapperResponse<MovieResponse> {
        return moviesApi.getPopular(queries)
    }

    override suspend fun fetchSimilar(movieId: Int): List<Movie> {
        return moviesApi.getSimilar(movieId).results?.map {
            it.toMovieModel()
        } ?: emptyList()
    }

    override suspend fun fetchSearch(query: String): List<Movie> {
        return moviesApi.getSearch(query).results?.map {
            it.toMovieModel()
        } ?: emptyList()
    }
}