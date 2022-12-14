package com.marcos.moviesmr.framework.network.response

import com.google.gson.annotations.SerializedName
import com.marcos.moviesmr.core.domain.Popular

data class PopularResponse(
    val id: String,
    val title: String,
    @SerializedName("release_date")
    val year: String,
    @SerializedName("poster_path")
    val imageUrl: String
)

fun PopularResponse.toMoviesModel(): Popular {
    return Popular(
        title = this.title,
        year = this.year,
        imageUrl = this.imageUrl.replace("http", "https")
    )
}
