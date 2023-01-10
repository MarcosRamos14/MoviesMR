package com.marcos.moviesmr.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.databinding.ItemMoviesHomeBinding
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
        itemMoviesHomeBinding: ItemMoviesHomeBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemMoviesHomeBinding.root) {

        private val textName = itemMoviesHomeBinding.textName
        private val textYear = itemMoviesHomeBinding.textYear
        private val imageMovies = itemMoviesHomeBinding.imageMovies

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
                val itemMoviesHomeBinding = ItemMoviesHomeBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return DetailSimilarViewHolder(itemMoviesHomeBinding, imageLoader)
            }
        }
    }
}