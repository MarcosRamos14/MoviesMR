package com.marcos.moviesmr.presentation.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.marcos.moviesmr.databinding.FragmentDetailBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailViewArg = args.detailViewArgs
        binding.imageMovies.run {
            imageLoader.load(this, detailViewArg.imageUrl)
        }
        binding.textDetailTitle.text = detailViewArg.title
        binding.numberLikeDetail.text = detailViewArg.likes.toString()
        binding.popularityDetail.text = detailViewArg.popularity.toString()

        setSharedElementTransitionOnEnter()

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                DetailViewModel.UiState.Loading -> {
                }
                is DetailViewModel.UiState.Success -> binding.recyclerMoviesDetail.run {
                    setHasFixedSize(true)
                    adapter = DetailSimilarAdapter(uiState.similar, imageLoader)
                }
                DetailViewModel.UiState.Error -> {
                }
                DetailViewModel.UiState.Empty -> {
                }
            }
        }

        viewModel.getSimilar(detailViewArg.popularId)
    }

    private fun setSharedElementTransitionOnEnter() {
        TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move).apply {
                sharedElementEnterTransition = this
            }
    }
}