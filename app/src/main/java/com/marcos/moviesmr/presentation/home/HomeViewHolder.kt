package com.marcos.moviesmr.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcos.moviesmr.R
import com.marcos.moviesmr.core.domain.model.Popular
import com.marcos.moviesmr.databinding.ItemMoviesHomeBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader

class HomeViewHolder(
    itemMoviesHomeBinding: ItemMoviesHomeBinding,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(itemMoviesHomeBinding.root) {

    private val textName = itemMoviesHomeBinding.textName
    private val textLanguage = itemMoviesHomeBinding.textLanguage
    private val imageMovies = itemMoviesHomeBinding.imageMovies

    fun bind(movies: Popular) {
        textName.text = movies.title
        textLanguage.text = movies.year
        imageLoader.load(imageMovies, movies.imageUrl, R.drawable.ic_img_loading_error)
    }

    companion object {
        fun create(parent: ViewGroup, imageLoader: ImageLoader) : HomeViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val itemBinding = ItemMoviesHomeBinding.inflate(inflate, parent, false)
            return HomeViewHolder(itemBinding, imageLoader)
        }
    }
}