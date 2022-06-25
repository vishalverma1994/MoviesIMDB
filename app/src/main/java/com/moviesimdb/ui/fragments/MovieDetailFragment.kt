package com.moviesimdb.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moviesimdb.databinding.FragmentMovieDetailBinding
import com.moviesimdb.utils.Constants

class MovieDetailFragment: Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private var moviesId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        extractArguments()
    }

    private fun extractArguments() {
        moviesId = requireArguments().getInt(Constants.ARG_1)
    }
}