package com.marcos.moviesmr.framework.di

import com.marcos.moviesmr.core.data.repository.PopularRemoteDataSource
import com.marcos.moviesmr.core.data.repository.PopularRepository
import com.marcos.moviesmr.framework.PopularRepositoryImpl
import com.marcos.moviesmr.framework.remote.RetrofitRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPopularRepository(repository: PopularRepositoryImpl): PopularRepository

    @Binds
    fun bindPopularRemoteDataSource(dataSource: RetrofitRemoteDataSource): PopularRemoteDataSource
}