package com.marcos.moviesmr.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.databinding.ItemMoviesBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import com.marcos.moviesmr.utils.OnItemClick

class HomeViewHolder(
    itemMoviesBinding: ItemMoviesBinding,
    private val imageLoader: ImageLoader,
    private val onItemClick: OnItemClick
) : RecyclerView.ViewHolder(itemMoviesBinding.root) {

    private val textName = itemMoviesBinding.textName
    private val textYear = itemMoviesBinding.textYear
    private val imageMovies = itemMoviesBinding.imageMovies

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
            onItemClick: OnItemClick
        ) : HomeViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val itemBinding = ItemMoviesBinding.inflate(inflate, parent, false)
            return HomeViewHolder(itemBinding, imageLoader, onItemClick)
        }
    }
}