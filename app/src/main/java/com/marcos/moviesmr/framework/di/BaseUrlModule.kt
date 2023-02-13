package com.marcos.moviesmr.framework.di

import com.marcos.moviesmr.BuildConfig
import com.marcos.moviesmr.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() : String = BuildConfig.BASE_URL
}