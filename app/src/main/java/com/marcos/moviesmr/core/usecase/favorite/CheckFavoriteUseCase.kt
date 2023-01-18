package com.marcos.moviesmr.core.usecase.favorite

import com.marcos.moviesmr.core.data.repository.FavoriteRepository
import com.marcos.moviesmr.core.usecase.base.CoroutinesDispatchers
import com.marcos.moviesmr.core.usecase.base.ResultStatus
import com.marcos.moviesmr.core.usecase.base.UseCase
import com.marcos.moviesmr.core.usecase.favorite.CheckFavoriteUseCase.Params
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CheckFavoriteUseCase {

    operator fun invoke(params: Params) : Flow<ResultStatus<Boolean>>

    data class Params(val movieId: Int)
}

class CheckFavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<Params, Boolean>(), CheckFavoriteUseCase  {

    override suspend fun doWork(params: Params): ResultStatus<Boolean> {
        return withContext(dispatchers.io()) {
            val isFavorite = favoriteRepository.isFavorite(params.movieId)
            ResultStatus.Success(isFavorite)
        }
    }
}