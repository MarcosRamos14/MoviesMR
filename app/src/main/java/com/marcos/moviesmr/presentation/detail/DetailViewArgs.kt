package com.marcos.moviesmr.presentation.detail

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DetailViewArgs(
    val name: String,
    val imageUrl: String
) : Parcelable
