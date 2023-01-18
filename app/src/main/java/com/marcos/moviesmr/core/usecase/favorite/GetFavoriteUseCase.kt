package com.marcos.moviesmr.core.usecase.favorite

import com.marcos.moviesmr.core.data.repository.FavoriteRepository
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.core.usecase.base.CoroutinesDispatchers
import com.marcos.moviesmr.core.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetFavoriteUseCase {

    suspend operator fun invoke(params: Unit = Unit) : Flow<List<Movie>>
}

class GetFavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val dispatchers: CoroutinesDispatchers
) : FlowUseCase<Unit, List<Movie>>(), GetFavoriteUseCase {

    override suspend fun createFlowObservable(params: Unit) : Flow<List<Movie>> {
        return withContext(dispatchers.io()) {
            favoriteRepository.getAll()
        }
    }
}