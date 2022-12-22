package com.marcos.moviesmr.core.data.repository

import com.marcos.moviesmr.framework.network.response.DataWrapperResponse

interface PopularRemoteDataSource {

    suspend fun fetchPopular(queries: Map<String, String>) : DataWrapperResponse
}