package com.marcos.moviesmr.framework.network.response

import com.google.gson.annotations.SerializedName
import com.marcos.moviesmr.core.domain.model.Movie

data class MovieResponse(
    val id: Int?,
    val title: String?,
    @SerializedName("release_date")
    val year: String?,
    @SerializedName("poster_path")
    val imageUrl: String?,
    @SerializedName("vote_count")
    val likes: Int?,
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
