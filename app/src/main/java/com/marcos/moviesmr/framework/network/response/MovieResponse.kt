package com.marcos.moviesmr.framework.network.response

import com.google.gson.annotations.SerializedName
import com.marcos.moviesmr.core.domain.model.Movie

data class MovieResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("release_date")
    val year: String?,
    @SerializedName("poster_path")
    val imageUrl: String?,
    @SerializedName("vote_count")
    val likes: Int?,
    @SerializedName("popularity")
    val popularity: Double?
)

fun MovieResponse.toMovieModel() : Movie {
    return Movie(
        id = this.id,
        title = this.title,
        year = this.year,
        imageUrl = this.imageUrl,
        likes = this.likes,
        popularity = this.popularity
    )
}