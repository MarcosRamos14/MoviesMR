package com.marcos.moviesmr.framework

import androidx.paging.PagingSource
import com.marcos.moviesmr.core.data.repository.PopularRemoteDataSource
import com.marcos.moviesmr.core.data.repository.PopularRepository
import com.marcos.moviesmr.core.domain.model.Popular
import com.marcos.moviesmr.framework.paging.PopularPagingSource
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val remoteDataSource: PopularRemoteDataSource
): PopularRepository {

    override fun getPopular(query: String): PagingSource<Int, Popular> {
        return PopularPagingSource(remoteDataSource)
    }
}