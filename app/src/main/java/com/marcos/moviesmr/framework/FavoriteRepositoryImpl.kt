package com.marcos.moviesmr.framework

import com.marcos.moviesmr.core.data.repository.FavoriteLocalDataSource
import com.marcos.moviesmr.core.data.repository.FavoriteRepository
import com.marcos.moviesmr.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource
) : FavoriteRepository {

    override fun getAll(): Flow<List<Movie>> {
        return favoriteLocalDataSource.getAll()
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return favoriteLocalDataSource.isFavorite(movieId)
    }

    override suspend fun saveFavorite(movie: Movie) {
        favoriteLocalDataSource.save(movie)
    }

    override suspend fun deleteFavorite(movie: Movie) {
        favoriteLocalDataSource.delete(movie)
    }
}