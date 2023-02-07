package com.marcos.moviesmr.core.domain.model

import com.marcos.moviesmr.presentation.detail.DetailViewArgs

data class Movie(
    val id: Int?,
    val title: String?,
    val year: String?,
    val imageUrl: String?,
    val likes: Int?,
    val popularity: Double?
) {
    fun toDetailViewArgs() = DetailViewArgs(
        movieId = id,
        title = title,
        year = year,
        imageUrl = imageUrl,
        likes = likes,
        popularity = popularity
    )
}