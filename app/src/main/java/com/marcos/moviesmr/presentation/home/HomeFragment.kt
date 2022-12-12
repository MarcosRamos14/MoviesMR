package com.marcos.moviesmr.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcos.moviesmr.core.domain.Movies
import com.marcos.moviesmr.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }

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

        homeAdapter.submitList(
            listOf(
                Movies("Velozes e Furiosos", "2001","https://img.elo7.com.br/product/zoom/268DCC4/big-poster-velozes-e-furiosos-lo06-tamanho-90x60-cm-velozes-e-furiosos.jpg"),
                Movies("The Simpson", "2007", "https://br.web.img2.acsta.net/medias/nmedia/18/92/12/47/20181896.jpg"),
                Movies("Até o Último Homem", "2016", "https://br.web.img3.acsta.net/pictures/16/11/21/15/29/457312.jpg"),
                Movies("Forrest Gump", "1994", "https://upload.wikimedia.org/wikipedia/pt/c/c0/ForrestGumpPoster.jpg"),
                Movies("Spider Man", "2021", "https://m.media-amazon.com/images/I/91THlJb4lvL.jpg")
            )
        )
    }

    private fun initHomeAdapter() {
        with(binding.recyclerMoviesHome) {
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }
}