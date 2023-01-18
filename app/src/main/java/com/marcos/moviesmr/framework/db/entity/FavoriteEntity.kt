package com.marcos.moviesmr.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.framework.db.DbConstants

@Entity(tableName = DbConstants.FAVORITES_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_TITLE)
    val title: String,
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_YEAR)
    val year: String,
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_IMAGE_URL)
    val imageUrl: String,
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_LIKES)
    val likes: Int,
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_POPULARITY)
    val popularity: Double
)

fun List<FavoriteEntity>.toMovieModel() = map {
    Movie(it.id, it.title, it.year, it.imageUrl, it.likes, it.popularity)
}