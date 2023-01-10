package com.marcos.moviesmr.core.data.repository

import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.framework.network.response.DataWrapperResponse
import com.marcos.moviesmr.framework.network.response.MovieResponse

interface PopularRemoteDataSource {

    suspend fun fetchPopular(queries: Map<String, String>) : DataWrapperResponse<MovieResponse>

    suspend fun fetchSimilar(movieId: Int) : List<Movie>
}