package com.marcos.moviesmr.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcos.moviesmr.R
import com.marcos.moviesmr.databinding.FragmentDetailBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val detailAdapter: DetailAdapter by lazy { DetailAdapter(imageLoader) }

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
    }

    private fun initDetailAdapter() {
        with(binding.recyclerMoviesDetail) {
            setHasFixedSize(true)
            adapter = detailAdapter
        }
    }
}