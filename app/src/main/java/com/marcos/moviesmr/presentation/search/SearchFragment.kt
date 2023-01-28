package com.marcos.moviesmr.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcos.moviesmr.core.domain.model.Movie
import com.marcos.moviesmr.databinding.FragmentSearchBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchAdapter: SearchAdapter by lazy {
        val adapter = SearchAdapter(imageLoader)
        binding.recyclerSearch.adapter = adapter
        return@lazy adapter
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentSearchBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter.submitList(
            listOf(
               Movie(24428, "The Avengers", "2012-04-25", "/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg", 8503, 7.353212),
               Movie(24428, "The Avengers", "2012-04-25", "/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg", 8503, 7.353212),
               Movie(24428, "The Avengers", "2012-04-25", "/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg", 8503, 7.353212)
            )
        )
    }
}