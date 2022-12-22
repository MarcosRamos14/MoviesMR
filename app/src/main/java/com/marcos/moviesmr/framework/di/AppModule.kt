package com.marcos.moviesmr.framework.di

import com.marcos.moviesmr.framework.imageLoader.GlideImageLoader
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface AppModule {

    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoader) : ImageLoader
}