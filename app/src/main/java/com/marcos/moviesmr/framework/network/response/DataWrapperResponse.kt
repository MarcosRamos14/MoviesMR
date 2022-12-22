package com.marcos.moviesmr.framework.network.response

data class DataWrapperResponse(
    val page: Int?,
    val total: Int?,
    val results: List<PopularResponse>?
)