package com.marcos.moviesmr.framework.network

import com.marcos.moviesmr.framework.network.response.MovieResponse
import com.marcos.moviesmr.framework.network.response.PagedDataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MoviesApi {

    @GET("popular")
    suspend fun getPopular(
        @QueryMap queries: Map<String, String>
    ) : PagedDataWrapperResponse<MovieResponse>

    @GET("{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movieId: Int
    ) : PagedDataWrapperResponse<MovieResponse>

    @GET("search/movie")
    suspend fun getSearch(
        @QueryMap query: Map<String, String>
    ) : PagedDataWrapperResponse<MovieResponse>
}