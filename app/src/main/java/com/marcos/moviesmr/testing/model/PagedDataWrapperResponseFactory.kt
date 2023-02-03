package com.marcos.moviesmr.testing.model

import com.marcos.moviesmr.framework.network.response.MovieResponse
import com.marcos.moviesmr.framework.network.response.PagedDataWrapperResponse

class PagedDataWrapperResponseFactory {

    fun create() = PagedDataWrapperResponse(
        page = 0,
        total = 2,
        results = listOf(
            MovieResponse(
                293660,
                "Deadpool",
                null,
                "https://image.tmdb.org/t/p/w500/nbIrDhOtUpdD9HKDBRy02a8VhpV.jpg",
                null,
                null,
            ),
            MovieResponse(
                168259,
                "Furious 7",
                null,
                "https://image.tmdb.org/t/p/w500/ypyeMfKydpyuuTMdp36rMlkGDUL.jpg",
                null,
                null
            )
        )
    )
}