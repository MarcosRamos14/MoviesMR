package com.marcos.moviesmr.core.data.repository

import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.framework.network.response.PagedDataWrapperResponse
import com.marcos.moviesmr.framework.network.response.MovieResponse

interface PopularRemoteDataSource {

    suspend fun fetchPopular(queries: Map<String, String>) : PagedDataWrapperResponse<MovieResponse>

    suspend fun fetchSimilar(movieId: Int) : List<Movie>
}