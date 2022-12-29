package com.marcos.moviesmr.presentation.detail

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.marcos.moviesmr.core.domain.model.Popular
import com.marcos.moviesmr.databinding.FragmentDetailBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader

class DetailAdapter(
    private val imageLoader: ImageLoader
) : PagingDataAdapter<Popular, DetailViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Popular>() {
            override fun areItemsTheSame(
                oldItem: Popular,
                newItem: Popular
            ) : Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: Popular,
                newItem: Popular
            ) : Boolean {
                return oldItem == newItem
            }
        }
    }
}