package com.marcos.moviesmr.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marcos.moviesmr.core.data.repository.PopularRepository
import com.marcos.moviesmr.core.domain.model.Popular
import com.marcos.moviesmr.core.usecase.GetPopularUseCase.GetPopularParams
import com.marcos.moviesmr.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularUseCase @Inject constructor(
    private val popularRepository: PopularRepository
): PagingUseCase<GetPopularParams, Popular>() {

    override fun createFlowObservable(params: GetPopularParams): Flow<PagingData<Popular>> {
        return Pager(config = params.pagingConfig) {
            popularRepository.getPopular(params.query)
        }.flow
    }

    data class GetPopularParams(val query: String, val pagingConfig: PagingConfig)
}
