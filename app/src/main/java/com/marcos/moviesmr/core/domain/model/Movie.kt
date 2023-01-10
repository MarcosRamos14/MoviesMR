package com.marcos.moviesmr.core.domain.model

data class Movie(
    val id: Int?,
    val title: String?,
    val year: String?,
    val imageUrl: String?,
    val likes: Int?,
    val popularity: Double?
)
