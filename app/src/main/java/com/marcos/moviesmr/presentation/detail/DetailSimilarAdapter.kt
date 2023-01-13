package com.marcos.moviesmr.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.databinding.ItemMoviesBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import com.marcos.moviesmr.presentation.detail.DetailSimilarAdapter.DetailSimilarViewHolder

class DetailSimilarAdapter(
    private val imageLoader: ImageLoader
) : ListAdapter<Movie, DetailSimilarViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailSimilarViewHolder {
        return DetailSimilarViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: DetailSimilarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DetailSimilarViewHolder(
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
            ) : DetailSimilarViewHolder {
                val itemMoviesBinding = ItemMoviesBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return DetailSimilarViewHolder(itemMoviesBinding, imageLoader)
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