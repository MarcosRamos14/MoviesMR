package com.marcos.moviesmr.di

import com.marcos.moviesmr.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlTestModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() : String = "http://localhost:8080/"
}