package com.marcos.moviesmr.core.data.repository

import androidx.paging.PagingSource
import com.marcos.moviesmr.core.domain.model.Movie

interface MoviesRepository {

    fun getPopular(query: String) : PagingSource<Int, Movie>

    suspend fun getSimilar(movieId: Int) : List<Movie>
}