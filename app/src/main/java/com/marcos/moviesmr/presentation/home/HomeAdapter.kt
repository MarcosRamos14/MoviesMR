package com.marcos.moviesmr.presentation.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.marcos.moviesmr.core.domain.model.Popular

class HomeAdapter : PagingDataAdapter<Popular, HomeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Popular>() {
            override fun areItemsTheSame(
                oldItem: Popular,
                newItem: Popular
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: Popular,
                newItem: Popular
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}