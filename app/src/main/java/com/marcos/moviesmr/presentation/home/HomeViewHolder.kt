package com.marcos.moviesmr.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcos.moviesmr.R
import com.marcos.moviesmr.core.domain.Movies
import com.marcos.moviesmr.databinding.ItemMoviesHomeBinding

class HomeViewHolder(
    itemMoviesHomeBinding: ItemMoviesHomeBinding
): RecyclerView.ViewHolder(itemMoviesHomeBinding.root) {

    private val textName = itemMoviesHomeBinding.textName
    private val textLanguage = itemMoviesHomeBinding.textLanguage
    private val imageMovies = itemMoviesHomeBinding.imageMovies

    fun bind(movies: Movies) {
        textName.text = movies.title
        textLanguage.text = movies.language
        Glide.with(itemView)
            .load(movies.imageUrl)
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