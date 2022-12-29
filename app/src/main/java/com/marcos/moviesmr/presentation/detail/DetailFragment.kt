package com.marcos.moviesmr.presentation.detail

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.marcos.moviesmr.R
import com.marcos.moviesmr.databinding.FragmentDetailBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val detailAdapter: DetailAdapter by lazy { DetailAdapter(imageLoader) }

    private val args by navArgs<DetailFragmentArgs>()

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
        initDetailAdapter()
        val detailViewArg = args.detailViewArgs
        binding.imageMovies.run {
            transitionName = detailViewArg.title
            imageLoader.load(this, detailViewArg.imageUrl)
        }
        setSharedElementTransitionOnEnter()
    }

    private fun setSharedElementTransitionOnEnter() {
        TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move).apply {
            sharedElementEnterTransition = this
        }
    }

    private fun initDetailAdapter() {
        with(binding.recyclerMoviesDetail) {
            setHasFixedSize(true)
            adapter = detailAdapter
        }
    }
}