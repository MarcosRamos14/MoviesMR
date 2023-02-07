package com.marcos.moviesmr.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.marcos.moviesmr.R
import com.marcos.moviesmr.databinding.FragmentSearchBinding
import com.marcos.moviesmr.framework.imageLoader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy {
        val adapter = SearchAdapter(imageLoader) { search, view ->
            val errorLoadingTitle = getString(R.string.error_loading_title)

            val extras = FragmentNavigatorExtras(
                view to (search.title ?: errorLoadingTitle)
            )

            val directions = SearchFragmentDirections
                .actionSearchFragmentToDetailFragment(
                    search.title ?: errorLoadingTitle,
                    search.toDetailViewArgs()
                )
            findNavController().navigate(directions, extras)
        }

        binding.recyclerSearch.adapter = adapter
        return@lazy adapter
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserverSearch()

        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            var job: Job? = null

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                job?.cancel()
                job = lifecycleScope.launch {
                    delay(1000L)
                    viewModel.getSearch(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.editTextSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                return false
            }
        })
    }

    private fun setupObserverSearch() {
        viewModel.stateSearch.observe(viewLifecycleOwner) { uiStateSearch ->
            binding.flipperSearch.displayedChild = when (uiStateSearch) {
                is SearchViewModel.UiStateSearch.Success -> {
                    searchAdapter.submitList(uiStateSearch.search)
                    FLIPPER_CHILD_POSITION_SEARCH
                }
                SearchViewModel.UiStateSearch.Empty -> FLIPPER_CHILD_POSITION_EMPTY
            }
        }
    }

    companion object {
        private const val FLIPPER_CHILD_POSITION_SEARCH = 0
        private const val FLIPPER_CHILD_POSITION_EMPTY = 1
    }
}