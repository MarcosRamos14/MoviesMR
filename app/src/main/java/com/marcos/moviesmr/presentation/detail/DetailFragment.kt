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
    private val detailSimilarAdapter: DetailSimilarAdapter by lazy {
        val adapter = DetailSimilarAdapter(imageLoader)
        binding.recyclerMoviesDetail.adapter = adapter
        return@lazy adapter
    }

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
        val detailViewArgs = args.detailViewArgs

        initSetupView(detailViewArgs)
        initSetupListener(detailViewArgs)
        initSetupObservers()
        setSharedElementTransitionOnEnter()

        viewModel.getSimilar(detailViewArgs.popularId)
    }

    private fun initSetupView(detailViewArgs: DetailViewArgs) {
        binding.imageMovies.run {
            transitionName = detailViewArgs.title
            imageLoader.load(this, detailViewArgs.imageUrl)
        }
        binding.textDetailTitle.text = detailViewArgs.title
        binding.numberLikeDetail.text = detailViewArgs.likes.toString()
        binding.popularityDetail.text = detailViewArgs.popularity.toString()
    }

    private fun initSetupListener(detailViewArgs: DetailViewArgs) {
        binding.includeErrorView.buttonRetry.setOnClickListener {
            viewModel.getSimilar(detailViewArgs.popularId)

        }
    }

    private fun initSetupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.flipperDetail.displayedChild = when (uiState) {
                DetailViewModel.UiState.Loading -> FLIPPER_CHILD_POSITION_LOADING
                is DetailViewModel.UiState.Success -> {
                    detailSimilarAdapter.submitList(uiState.similar)
                    FLIPPER_CHILD_POSITION_DETAIL
                }
                DetailViewModel.UiState.Error -> FLIPPER_CHILD_POSITION_ERROR
                DetailViewModel.UiState.Empty -> FLIPPER_CHILD_POSITION_EMPTY
            }
        }
    }

    private fun setSharedElementTransitionOnEnter() {
        TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move).apply {
                sharedElementEnterTransition = this
            }
    }

    companion object {
        private const val FLIPPER_CHILD_POSITION_LOADING = 0
        private const val FLIPPER_CHILD_POSITION_DETAIL = 1
        private const val FLIPPER_CHILD_POSITION_ERROR = 2
        private const val FLIPPER_CHILD_POSITION_EMPTY = 3
    }
}