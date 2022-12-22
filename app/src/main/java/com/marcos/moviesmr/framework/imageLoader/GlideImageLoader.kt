package com.marcos.moviesmr.framework.imageLoader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.marcos.moviesmr.BuildConfig
import javax.inject.Inject

class GlideImageLoader @Inject constructor() : ImageLoader {

    override fun load(imageView: ImageView, imageUrl: String?, fallback: Int) {
        imageUrl?.let {
            Glide.with(imageView.rootView)
                .load(BuildConfig.BASE_URL_IMAGE + it)
                .fallback(fallback)
                .into(imageView)
        }
    }
}