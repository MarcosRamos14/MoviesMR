package com.marcos.moviesmr.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marcos.moviesmr.core.data.repository.PopularRepository
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.core.usecase.GetPopularUseCase.GetPopularParams
import com.marcos.moviesmr.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPopularUseCase {

    operator fun invoke(params: GetPopularParams) : Flow<PagingData<Movie>>

    data class GetPopularParams(val query: String, val pagingConfig: PagingConfig)
}

class GetPopularUseCaseImpl @Inject constructor(
    private val popularRepository : PopularRepository
) : PagingUseCase<GetPopularParams, Movie>(), GetPopularUseCase {

    override fun createFlowObservable(params: GetPopularParams) : Flow<PagingData<Movie>> {
        return Pager(config = params.pagingConfig) {
            popularRepository.getPopular(params.query)
        }.flow
    }
}