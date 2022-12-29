package com.marcos.moviesmr.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.R
import com.marcos.moviesmr.core.domain.model.Popular
import com.marcos.moviesmr.databinding.ItemMoviesHomeBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import com.marcos.moviesmr.utils.OnHomeItemClick

class HomeViewHolder(
    itemMoviesHomeBinding: ItemMoviesHomeBinding,
    private val imageLoader: ImageLoader,
    private val onItemClick: OnHomeItemClick
) : RecyclerView.ViewHolder(itemMoviesHomeBinding.root) {

    private val textName = itemMoviesHomeBinding.textName
    private val textLanguage = itemMoviesHomeBinding.textLanguage
    private val imageMovies = itemMoviesHomeBinding.imageMovies

    fun bind(popular: Popular) {
        textName.text = popular.title
        textLanguage.text = popular.year
        imageMovies.transitionName = popular.title
        imageLoader.load(imageMovies, popular.imageUrl)

        itemView.setOnClickListener {
            onItemClick.invoke(popular, imageMovies)
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