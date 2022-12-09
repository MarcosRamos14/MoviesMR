package com.marcos.moviesmr.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcos.moviesmr.R
import com.marcos.moviesmr.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

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
}