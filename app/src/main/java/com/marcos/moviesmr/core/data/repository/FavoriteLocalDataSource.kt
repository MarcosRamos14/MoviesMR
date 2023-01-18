package com.marcos.moviesmr.core.data.repository

import com.marcos.moviesmr.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {

    fun getAll() : Flow<List<Movie>>

    suspend fun isFavorite(movieId: Int) : Boolean

    suspend fun save(movie: Movie)

    suspend fun delete(movie: Movie)
}