package com.marcos.moviesmr.core.data.repository

import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.framework.network.response.MovieResponse
import com.marcos.moviesmr.framework.network.response.PagedDataWrapperResponse

interface MoviesRemoteDataSource {

    suspend fun fetchPopular(queries: Map<String, String>) : PagedDataWrapperResponse<MovieResponse>

    suspend fun fetchSimilar(movieId: Int) : List<Movie>
}