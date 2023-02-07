package com.marcos.moviesmr.framework.network.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.databinding.ItemMoviesBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import com.marcos.moviesmr.framework.network.search.SearchAdapter.SearchViewHolder

class SearchAdapter(
    private val imageLoader: ImageLoader
) : ListAdapter<Movie, SearchViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SearchViewHolder {
        return SearchViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchViewHolder(
        itemMoviesBinding: ItemMoviesBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemMoviesBinding.root) {

        private val textName = itemMoviesBinding.textName
        private val textYear = itemMoviesBinding.textYear
        private val imageMovies = itemMoviesBinding.imageMovies

        fun bind(movie: Movie) {
            textName.text = movie.title
            textYear.text = movie.year
            imageLoader.load(imageMovies, movie.imageUrl)
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader
            ) : SearchViewHolder {
                val itemMoviesBinding = ItemMoviesBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return SearchViewHolder(itemMoviesBinding, imageLoader)
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