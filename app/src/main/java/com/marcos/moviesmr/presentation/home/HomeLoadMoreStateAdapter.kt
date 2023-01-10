package com.marcos.moviesmr.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marcos.moviesmr.databinding.ItemMovieLoadMoreStateBinding

class HomeLoadMoreStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<HomeLoadMoreStateAdapter.HomeLoadMoreStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, loadState: LoadState
    ) = HomeLoadMoreStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(holder: HomeLoadMoreStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    class HomeLoadMoreStateViewHolder(
        itemBinding: ItemMovieLoadMoreStateBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding = ItemMovieLoadMoreStateBinding.bind(itemView)
        private val progressBarLoadingMore = binding.progressLoadingMore
        private val textTryAgainMessage = binding.textTryAgain.also {
            it.setOnClickListener { retry() }
        }

        fun bind(loadState: LoadState) {
            progressBarLoadingMore.isVisible = loadState is LoadState.Loading
            textTryAgainMessage.isVisible = loadState is LoadState.Error
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): HomeLoadMoreStateViewHolder {
                val itemBinding = ItemMovieLoadMoreStateBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return HomeLoadMoreStateViewHolder(itemBinding, retry)
            }
        }
    }
}