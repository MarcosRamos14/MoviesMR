package com.marcos.moviesmr.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.marcos.moviesmr.R
import com.marcos.moviesmr.databinding.FragmentFavoritesBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()

    private val favoritesAdapter: FavoritesAdapter by lazy {
        val adapter = FavoritesAdapter(imageLoader) { favorites, view ->
            val errorLoadingTitle = getString(R.string.error_loading_title)

            val extras = FragmentNavigatorExtras(
                view to (favorites.title ?: errorLoadingTitle)
            )

            val directions = FavoritesFragmentDirections
                .actionFavoritesFragmentToDetailFragment(
                    favorites.title ?: errorLoadingTitle,
                    favorites.toDetailViewArgs()
                )

            findNavController().navigate(directions, extras)
        }
        return@lazy adapter
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFavoritesBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFavorites.adapter = favoritesAdapter
        setupObservers()
        viewModel.getAll()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { uiState ->
            binding.flipperScreenFavorite.displayedChild = when (uiState) {
                is FavoritesViewModel.UiState.ShowFavorite -> {
                    favoritesAdapter.submitList(uiState.favorites)
                    FLIPPER_CHILD_MOVIES_FAVORITES
                }
                FavoritesViewModel.UiState.ShowEmpty -> {
                    favoritesAdapter.submitList(emptyList())
                    FLIPPER_CHILD_FAVORITES_EMPTY
                }
            }
        }
    }

    companion object {
        private const val FLIPPER_CHILD_MOVIES_FAVORITES = 0
        private const val FLIPPER_CHILD_FAVORITES_EMPTY = 1
    }
}