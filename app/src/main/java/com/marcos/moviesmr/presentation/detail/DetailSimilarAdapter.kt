package com.marcos.moviesmr.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.databinding.ItemMoviesBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader

class DetailSimilarAdapter(
    private val detailSimilarList: List<Movie>,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<DetailSimilarAdapter.DetailSimilarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailSimilarViewHolder {
        return DetailSimilarViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: DetailSimilarViewHolder, position: Int) {
        holder.bind(detailSimilarList[position])
    }

    override fun getItemCount() = detailSimilarList.size

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
}