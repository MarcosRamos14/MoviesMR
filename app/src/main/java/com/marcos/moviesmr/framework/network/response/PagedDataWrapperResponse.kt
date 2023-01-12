package com.marcos.moviesmr.framework.network.response

import com.google.gson.annotations.SerializedName

data class PagedDataWrapperResponse<T>(
    val page: Int?,
    @SerializedName("total_pages")
    val total: Int?,
    val results: List<T>?
)