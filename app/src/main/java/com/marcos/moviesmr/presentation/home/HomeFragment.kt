package com.marcos.moviesmr.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.marcos.moviesmr.R
import com.marcos.moviesmr.databinding.FragmentHomeBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import com.marcos.moviesmr.presentation.detail.DetailViewArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private val homeAdapter: HomeAdapter by lazy {
        val homeAdapter = HomeAdapter(imageLoader) { popular, view ->
            val errorLoadingTitle = getString(R.string.error_loading_title)

            val extras = FragmentNavigatorExtras(
                view to (popular.title ?: errorLoadingTitle)
            )

            val directions = HomeFragmentDirections
                .actionHomeFragmentToDetailFragment(
                    popular.title ?: errorLoadingTitle,
                    DetailViewArgs(
                        movieId = popular.id ?: 0,
                        title = popular.title ?: errorLoadingTitle,
                        year = popular.year ?: errorLoadingTitle,
                        imageUrl = popular.imageUrl ?: errorLoadingTitle,
                        likes = popular.likes ?: 0,
                        popularity = popular.popularity ?: 0.0
                    )
                )
            findNavController().navigate(directions, extras)
        }
        return@lazy homeAdapter
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()
        observePagingState()
        setupListener()

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.popularPagingData("").collect { pagingData ->
                    homeAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initHomeAdapter() {
        with(binding.recyclerMoviesHome) {
            adapter = homeAdapter.withLoadStateFooter(
                footer = HomeLoadMoreStateAdapter(homeAdapter::retry)
            )
        }
    }

    private fun observePagingState() {
        lifecycleScope.launch {
            homeAdapter.loadStateFlow.collectLatest { loadState ->
                binding.flipperHome.displayedChild = when (loadState.refresh) {
                    is LoadState.Loading -> {
                        setShimmerVisibility(true)
                        FLIPPER_CHILD_LOADING
                    }
                    is LoadState.NotLoading -> {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_HOME
                    }
                    is LoadState.Error -> {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_ERROR
                    }
                }
            }
        }
    }

    private fun setupListener() {
        binding.includeViewHomeErrorState.buttonRetry.setOnClickListener {
            homeAdapter.retry()
        }
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewHomeLoadingState.shimmerMovieList.run {
            isVisible = visibility
            if (visibility) {
                startShimmer()
            } else stopShimmer()
        }
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_HOME = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }
}