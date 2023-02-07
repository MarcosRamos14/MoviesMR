package com.marcos.moviesmr.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.databinding.ItemMoviesBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import com.marcos.moviesmr.presentation.search.SearchAdapter.SearchViewHolder
import com.marcos.moviesmr.utils.OnItemClick

class SearchAdapter(
    private val imageLoader: ImageLoader,
    private val onItemClick: OnItemClick
) : ListAdapter<Movie, SearchViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SearchViewHolder {
        return SearchViewHolder.create(parent, imageLoader, onItemClick)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchViewHolder(
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
            imageMovies.run {
                transitionName = movie.title
                imageLoader.load(this, movie.imageUrl)
            }

            itemView.setOnClickListener {
                onItemClick.invoke(movie, imageMovies)
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader,
                onItemClick: OnItemClick
            ) : SearchViewHolder {
                val itemMoviesBinding = ItemMoviesBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return SearchViewHolder(itemMoviesBinding, imageLoader, onItemClick)
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