package com.marcos.moviesmr.core.usecase

import com.marcos.moviesmr.core.data.repository.MoviesRepository
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.core.usecase.base.ResultStatus
import com.marcos.moviesmr.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSearchUseCase {

    operator fun invoke(params: GetSearchParams) : Flow<ResultStatus<List<Movie>>>

    data class GetSearchParams(val query: String)
}

class GetSearchUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
) : GetSearchUseCase, UseCase<GetSearchUseCase.GetSearchParams, List<Movie>>() {

    override suspend fun doWork(
        params: GetSearchUseCase.GetSearchParams
    ): ResultStatus<List<Movie>> {
        val search = repository.getSearch(params.query)
        return ResultStatus.Success(search)
    }
}