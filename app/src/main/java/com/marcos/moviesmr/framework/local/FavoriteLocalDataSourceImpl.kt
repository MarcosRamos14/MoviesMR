package com.marcos.moviesmr.framework.local

import com.marcos.moviesmr.core.data.repository.FavoriteLocalDataSource
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.framework.db.dao.FavoriteDao
import com.marcos.moviesmr.framework.db.entity.FavoriteEntity
import com.marcos.moviesmr.framework.db.entity.toMovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteLocalDataSourceImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteLocalDataSource {

    override fun getAll(): Flow<List<Movie>> {
        return favoriteDao.loadFavorite().map {
            it.toMovieModel()
        }
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return favoriteDao.hasFavorite(movieId) != null
    }

    override suspend fun save(movie: Movie) {
        favoriteDao.insertFavorite(movie.toFavoriteEntity())
    }

    override suspend fun delete(movie: Movie) {
        favoriteDao.deleteFavorite(movie.toFavoriteEntity())
    }

    private fun Movie.toFavoriteEntity() =
        FavoriteEntity(id, title, year, imageUrl, likes, popularity)
}