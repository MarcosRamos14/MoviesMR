package com.marcos.moviesmr.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.databinding.ItemMoviesHomeBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import com.marcos.moviesmr.utils.OnHomeItemClick

class HomeViewHolder(
    itemMoviesHomeBinding: ItemMoviesHomeBinding,
    private val imageLoader: ImageLoader,
    private val onItemClick: OnHomeItemClick
) : RecyclerView.ViewHolder(itemMoviesHomeBinding.root) {

    private val textName = itemMoviesHomeBinding.textName
    private val textYear = itemMoviesHomeBinding.textYear
    private val imageMovies = itemMoviesHomeBinding.imageMovies

    fun bind(movie: Movie) {
        textName.text = movie.title
        textYear.text = movie.year
        imageMovies.transitionName = movie.title
        imageLoader.load(imageMovies, movie.imageUrl)

        itemView.setOnClickListener {
            onItemClick.invoke(movie, imageMovies)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            imageLoader: ImageLoader,
            onItemClick: OnHomeItemClick
        ) : HomeViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val itemBinding = ItemMoviesHomeBinding.inflate(inflate, parent, false)
            return HomeViewHolder(itemBinding, imageLoader, onItemClick)
        }
    }
}