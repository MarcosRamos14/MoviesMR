package com.marcos.moviesmr.presentation.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import com.marcos.moviesmr.utils.OnItemClick

class HomeAdapter(
    private val imageLoader: ImageLoader,
    private val onItemClick: OnItemClick
) : PagingDataAdapter<Movie, HomeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : HomeViewHolder {
        return HomeViewHolder.create(parent, imageLoader, onItemClick)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
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