package com.marcos.moviesmr.framework.network.response

data class DataWrapperResponse(
    val page: Int,
    val results: List<PopularResponse>
)