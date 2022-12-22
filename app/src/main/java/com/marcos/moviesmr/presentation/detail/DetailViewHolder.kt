package com.marcos.moviesmr.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.R
import com.marcos.moviesmr.core.domain.model.Popular
import com.marcos.moviesmr.databinding.ItemMoviesDetailBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader

class DetailViewHolder(
    itemMoviesDetailBinding: ItemMoviesDetailBinding,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(itemMoviesDetailBinding.root) {

    private val textName = itemMoviesDetailBinding.textName
    private val textLanguage = itemMoviesDetailBinding.textLanguage
    private val imageMovies = itemMoviesDetailBinding.imageMovies

    fun bind(movies: Popular) {
        textName.text = movies.title
        textLanguage.text = movies.year
        imageLoader.load(imageMovies, movies.imageUrl, R.drawable.ic_img_loading_error)
    }

    companion object {
        fun create(parent: ViewGroup, imageLoader: ImageLoader) : DetailViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val itemBinding = ItemMoviesDetailBinding.inflate(inflate, parent, false)
            return DetailViewHolder(itemBinding, imageLoader)
        }
    }
}