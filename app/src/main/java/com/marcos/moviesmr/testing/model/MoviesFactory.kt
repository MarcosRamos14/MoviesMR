package com.marcos.moviesmr.testing.model

import com.marcos.moviesmr.core.domain.model.Movie

class MoviesFactory {

    fun create(popular: Popular) = when (popular) {
        Popular.Deadpool -> Movie(
            293660,
            "Deadpool",
            null,
            "https://image.tmdb.org/t/p/w500/nbIrDhOtUpdD9HKDBRy02a8VhpV.jpg",
            null,
            null,
        )
        Popular.Furious7 -> Movie(
            168259,
            "Furious 7",
            null,
            "https://image.tmdb.org/t/p/w500/ypyeMfKydpyuuTMdp36rMlkGDUL.jpg",
            null,
            null
        )
    }

    sealed class Popular {
        object Deadpool : Popular()
        object Furious7 : Popular()
    }
}