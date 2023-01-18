package com.marcos.moviesmr.framework.di

import com.marcos.moviesmr.core.usecase.GetSimilarUseCase
import com.marcos.moviesmr.core.usecase.GetSimilarUseCaseImpl
import com.marcos.moviesmr.core.usecase.favorite.AddFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.AddFavoriteUseCaseImpl
import com.marcos.moviesmr.core.usecase.favorite.CheckFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.CheckFavoriteUseCaseImpl
import com.marcos.moviesmr.core.usecase.favorite.RemoveFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.RemoveFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetSimilarUseCase(useCase: GetSimilarUseCaseImpl) : GetSimilarUseCase

    @Binds
    fun bindCheckFavoriteUseCase(useCase: CheckFavoriteUseCaseImpl) : CheckFavoriteUseCase

    @Binds
    fun bindAddFavoriteUseCase(useCase: AddFavoriteUseCaseImpl) : AddFavoriteUseCase

    @Binds
    fun bindRemoveFavoriteUseCase(useCase: RemoveFavoriteUseCaseImpl) : RemoveFavoriteUseCase
}