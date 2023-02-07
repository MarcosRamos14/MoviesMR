package com.marcos.moviesmr.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.databinding.ItemMoviesBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import com.marcos.moviesmr.presentation.favorites.FavoritesAdapter.FavoritesViewHolder
import com.marcos.moviesmr.utils.OnHomeItemClick

class FavoritesAdapter(
    private val imageLoader: ImageLoader,
    private val onItemClick: OnHomeItemClick
) : ListAdapter<Movie, FavoritesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder.create(parent, imageLoader, onItemClick)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavoritesViewHolder(
        itemMoviesBinding: ItemMoviesBinding,
        private val imageLoader: ImageLoader,
        private val onItemClick: OnHomeItemClick
    ) : RecyclerView.ViewHolder(itemMoviesBinding.root) {

        private val textName = itemMoviesBinding.textName
        private val textYear = itemMoviesBinding.textYear
        private val imageMovies = itemMoviesBinding.imageMovies

        fun bind(movie: Movie) {
            textName.text = movie.title
            textYear.text = movie.year
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
            ) : FavoritesViewHolder {
                val itemMoviesBinding = ItemMoviesBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return FavoritesViewHolder(itemMoviesBinding, imageLoader, onItemClick)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ) : Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ) : Boolean {
                return oldItem == newItem
            }
        }
    }
}