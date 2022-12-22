package com.marcos.moviesmr.core.data.repository

import androidx.paging.PagingSource
import com.marcos.moviesmr.core.domain.model.Popular

interface PopularRepository {

    fun getPopular(query: String) : PagingSource<Int, Popular>
}