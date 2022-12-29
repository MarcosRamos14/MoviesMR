package com.marcos.moviesmr.framework.imageLoader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.marcos.moviesmr.R

interface ImageLoader {

    fun load(
        imageView: ImageView,
        imageUrl: String?,
        @DrawableRes placeholder: Int = R.drawable.ic_img_loading,
        @DrawableRes fallback: Int = R.drawable.ic_img_loading_error
    )
}