package com.marcos.moviesmr.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marcos.moviesmr.framework.db.dao.FavoriteDao
import com.marcos.moviesmr.framework.db.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao() : FavoriteDao
}