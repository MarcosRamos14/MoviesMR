package com.marcos.moviesmr.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcos.moviesmr.R
import com.marcos.moviesmr.core.domain.model.Popular
import com.marcos.moviesmr.databinding.ItemMoviesHomeBinding

class HomeViewHolder(
    itemMoviesHomeBinding: ItemMoviesHomeBinding
): RecyclerView.ViewHolder(itemMoviesHomeBinding.root) {

    private val textName = itemMoviesHomeBinding.textName
    private val textLanguage = itemMoviesHomeBinding.textLanguage
    private val imageMovies = itemMoviesHomeBinding.imageMovies

    fun bind(movies: Popular) {
        textName.text = movies.title
        textLanguage.text = movies.year
        Glide.with(itemView)
            .load("https://image.tmdb.org/t/p/w500" + movies.imageUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(imageMovies)
    }

    companion object {
        fun create(parent: ViewGroup): HomeViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val itemBinding = ItemMoviesHomeBinding.inflate(inflate, parent, false)
            return HomeViewHolder(itemBinding)
        }
    }
}