package com.marcos.moviesmr.framework.remote

import com.marcos.moviesmr.core.data.repository.PopularRemoteDataSource
import com.marcos.moviesmr.framework.network.MoviesApi
import com.marcos.moviesmr.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class RetrofitRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
): PopularRemoteDataSource {

    override suspend fun fetchPopular(queries: Map<String, String>): DataWrapperResponse {
        return moviesApi.getPopular(queries)
    }
}