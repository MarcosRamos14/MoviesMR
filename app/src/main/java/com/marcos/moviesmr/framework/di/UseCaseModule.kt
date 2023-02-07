package com.marcos.moviesmr.framework.di

import com.marcos.moviesmr.core.usecase.GetPopularUseCase
import com.marcos.moviesmr.core.usecase.GetPopularUseCaseImpl
import com.marcos.moviesmr.core.usecase.GetSearchUseCase
import com.marcos.moviesmr.core.usecase.GetSearchUseCaseImpl
import com.marcos.moviesmr.core.usecase.GetSimilarUseCase
import com.marcos.moviesmr.core.usecase.GetSimilarUseCaseImpl
import com.marcos.moviesmr.core.usecase.favorite.AddFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.AddFavoriteUseCaseImpl
import com.marcos.moviesmr.core.usecase.favorite.CheckFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.CheckFavoriteUseCaseImpl
import com.marcos.moviesmr.core.usecase.favorite.GetFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.GetFavoriteUseCaseImpl
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
    fun bindGetPopularUseCase(useCase: GetPopularUseCaseImpl) : GetPopularUseCase

    @Binds
    fun bindGetSimilarUseCase(useCase: GetSimilarUseCaseImpl) : GetSimilarUseCase

    @Binds
    fun bindGetSearchUseCase(useCase: GetSearchUseCaseImpl) : GetSearchUseCase

    @Binds
    fun bindCheckFavoriteUseCase(useCase: CheckFavoriteUseCaseImpl) : CheckFavoriteUseCase

    @Binds
    fun bindAddFavoriteUseCase(useCase: AddFavoriteUseCaseImpl) : AddFavoriteUseCase

    @Binds
    fun bindRemoveFavoriteUseCase(useCase: RemoveFavoriteUseCaseImpl) : RemoveFavoriteUseCase

    @Binds
    fun bindGetFavoriteUseCase(useCase: GetFavoriteUseCaseImpl) : GetFavoriteUseCase
}