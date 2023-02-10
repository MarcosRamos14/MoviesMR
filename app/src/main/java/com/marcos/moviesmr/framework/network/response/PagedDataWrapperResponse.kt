package com.marcos.moviesmr.framework.network.response

import com.google.gson.annotations.SerializedName

data class PagedDataWrapperResponse<T>(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_pages")
    val total: Int?,
    @SerializedName("results")
    val results: List<T>?
)