package com.marcos.moviesmr.framework.di

import com.marcos.moviesmr.core.data.repository.FavoriteLocalDataSource
import com.marcos.moviesmr.core.data.repository.FavoriteRepository
import com.marcos.moviesmr.core.data.repository.MoviesRemoteDataSource
import com.marcos.moviesmr.core.data.repository.MoviesRepository
import com.marcos.moviesmr.framework.FavoriteRepositoryImpl
import com.marcos.moviesmr.framework.MoviesRepositoryImpl
import com.marcos.moviesmr.framework.local.FavoriteLocalDataSourceImpl
import com.marcos.moviesmr.framework.remote.MoviesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPopularRepository(repository: MoviesRepositoryImpl) : MoviesRepository

    @Binds
    fun bindPopularRemoteDataSource(dataSource: MoviesRemoteDataSourceImpl) : MoviesRemoteDataSource

    @Binds
    fun bindFavoriteRepository(repository: FavoriteRepositoryImpl) : FavoriteRepository

    @Binds
    fun bindFavoriteLocalDataSource(dataSource: FavoriteLocalDataSourceImpl) : FavoriteLocalDataSource
}