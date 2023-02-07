package com.marcos.moviesmr.presentation.detail

import android.os.Parcelable
import androidx.annotation.Keep
import com.marcos.moviesmr.core.usecase.favorite.AddFavoriteUseCase
import com.marcos.moviesmr.core.usecase.favorite.RemoveFavoriteUseCase
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class
DetailViewArgs(
    val movieId: Int?,
    val title: String?,
    val year: String?,
    val imageUrl: String?,
    val likes: Int?,
    val popularity: Double?
) : Parcelable {

    fun toAddFavoriteUseCaseParams() = AddFavoriteUseCase.Params(
        movieId = movieId,
        title = title,
        year = year,
        imageUrl = imageUrl,
        likes = likes,
        popularity = popularity
    )
    fun toRemoveFavoriteUseCaseParams() = RemoveFavoriteUseCase.Params(
        movieId = movieId,
        title = title,
        year = year,
        imageUrl = imageUrl,
        likes = likes,
        popularity = popularity
    )
}