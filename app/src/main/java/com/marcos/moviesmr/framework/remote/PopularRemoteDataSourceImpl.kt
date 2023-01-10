package com.marcos.moviesmr.framework.remote

import com.marcos.moviesmr.core.data.repository.PopularRemoteDataSource
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.framework.network.MoviesApi
import com.marcos.moviesmr.framework.network.response.DataWrapperResponse
import com.marcos.moviesmr.framework.network.response.MovieResponse
import com.marcos.moviesmr.framework.network.response.toMovieModel
import javax.inject.Inject

class PopularRemoteDataSourceImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : PopularRemoteDataSource {

    override suspend fun fetchPopular(
        queries: Map<String, String>
    ) : DataWrapperResponse<MovieResponse> {
        return moviesApi.getPopular(queries)
    }

    override suspend fun fetchSimilar(movieId: Int): List<Movie> {
        return moviesApi.getSimilar(movieId).results?.map {
            it.toMovieModel()
        } ?: emptyList()
    }
}