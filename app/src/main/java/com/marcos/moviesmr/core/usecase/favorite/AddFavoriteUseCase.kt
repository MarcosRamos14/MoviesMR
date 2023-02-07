package com.marcos.moviesmr.core.usecase.favorite

import com.marcos.moviesmr.core.data.repository.FavoriteRepository
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.core.usecase.base.CoroutinesDispatchers
import com.marcos.moviesmr.core.usecase.base.ResultStatus
import com.marcos.moviesmr.core.usecase.base.UseCase
import com.marcos.moviesmr.core.usecase.favorite.AddFavoriteUseCase.Params
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AddFavoriteUseCase {

    operator fun invoke(params: Params) : Flow<ResultStatus<Unit>>

    data class Params(
        val movieId: Int?,
        val title: String?,
        val year: String?,
        val imageUrl: String?,
        val likes: Int?,
        val popularity: Double?
    )
}

class AddFavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<Params, Unit>(), AddFavoriteUseCase {

    override suspend fun doWork(params: Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            favoriteRepository.saveFavorite(
                Movie(
                    params.movieId,
                    params.title,
                    params.year,
                    params.imageUrl,
                    params.likes,
                    params.popularity
                )
            )
            ResultStatus.Success(Unit)
        }
    }
}