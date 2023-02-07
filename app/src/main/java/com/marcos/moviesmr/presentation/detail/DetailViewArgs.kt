package com.marcos.moviesmr.presentation.detail

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class
DetailViewArgs(
    val movieId: Int,
    val title: String,
    val year: String,
    val imageUrl: String,
    val likes: Int,
    val popularity: Double
) : Parcelable
