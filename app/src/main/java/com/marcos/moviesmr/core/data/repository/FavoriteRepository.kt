package com.marcos.moviesmr.core.data.repository

import com.marcos.moviesmr.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getAll() : Flow<List<Movie>>

    suspend fun isFavorite(movieId: Int) : Boolean

    suspend fun saveFavorite(movie: Movie)

    suspend fun deleteFavorite(movie: Movie)
}