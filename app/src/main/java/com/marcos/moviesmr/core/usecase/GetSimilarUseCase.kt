package com.marcos.moviesmr.core.usecase

import com.marcos.moviesmr.core.data.repository.MoviesRepository
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.core.usecase.base.ResultStatus
import com.marcos.moviesmr.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSimilarUseCase {

    operator fun invoke(params: GetSimilarParams) : Flow<ResultStatus<List<Movie>>>

    data class GetSimilarParams(val movieId: Int)
}

class GetSimilarUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
) : GetSimilarUseCase, UseCase<GetSimilarUseCase.GetSimilarParams, List<Movie>>() {

    override suspend fun doWork(
        params: GetSimilarUseCase.GetSimilarParams
    ): ResultStatus<List<Movie>> {
        val similar = repository.getSimilar(params.movieId)
        return ResultStatus.Success(similar)
    }
}