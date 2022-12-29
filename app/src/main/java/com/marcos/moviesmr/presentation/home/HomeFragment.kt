package com.marcos.moviesmr.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.marcos.moviesmr.R
import com.marcos.moviesmr.databinding.FragmentHomeBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import com.marcos.moviesmr.presentation.detail.DetailViewArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentHomeBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeAdapter()

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.popularPagingData("").collect { pagingData ->
                    homeAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(imageLoader) { popular, view ->
            val errorLoadingTitle = getString(R.string.error_loading_title)

            val extras = FragmentNavigatorExtras (
                view to (popular.title ?: errorLoadingTitle)
            )

            val directions = HomeFragmentDirections
                .actionHomeFragmentToDetailFragment(
                    popular.title ?: errorLoadingTitle,
                    DetailViewArgs(
                        popularId = popular.id,
                        title = popular.title ?: errorLoadingTitle,
                        imageUrl = popular.imageUrl
                    )
                )

            findNavController().navigate(directions, extras)
        }

        with(binding.recyclerMoviesHome) {
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }
}