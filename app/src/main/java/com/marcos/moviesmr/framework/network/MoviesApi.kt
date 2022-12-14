package com.marcos.moviesmr.framework.network

import com.marcos.moviesmr.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MoviesApi {

    @GET("popular")
    suspend fun getPopular(
        @QueryMap
        queries: Map<String, String>
    ) : DataWrapperResponse
}