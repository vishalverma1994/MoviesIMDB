package com.moviesimdb.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviesimdb.R
import com.moviesimdb.databinding.FragmentMoviesListBinding
import com.moviesimdb.model.MoviesResult
import com.moviesimdb.ui.adapter.MoviesListAdapter
import com.moviesimdb.utils.Constants
import com.moviesimdb.utils.Status
import com.moviesimdb.viewmodel.MoviesViewModel

class MoviesListFragment: Fragment() {

    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var moviesListAdapter: MoviesListAdapter
    private val moviesViewModel: MoviesViewModel by activityViewModels()
    private var moviesList = emptyList<MoviesResult>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerMoviesListLiveData()
        setMoviesRecyclerView()
        fetchMoviesList()
    }

    //observing liveData for fetching the data from network
    private fun observerMoviesListLiveData() {
        moviesViewModel.moviesListLiveData.observe(viewLifecycleOwner) { moviesListResponse ->
            when (moviesListResponse.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    moviesListResponse.data?.results?.let {
                        moviesList = it
                        moviesListAdapter.submitList(moviesList)
                    }
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    //setting the recycler view adapter
    private fun setMoviesRecyclerView() {
        binding.rvMoviesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            moviesListAdapter = MoviesListAdapter(::onItemClick)
            adapter = moviesListAdapter
        }
    }

    private fun onItemClick(position: Int) {
        if (moviesList.isNotEmpty()) {
            val bundle = bundleOf(Constants.ARG_1 to moviesList[position].id)
            findNavController().navigate(R.id.action_myMoviesListFragment_to_myMovieDetailFragment, bundle)
        }
    }

    //fetching movies list from network
    private fun fetchMoviesList() {
        moviesViewModel.fetchMoviesList(1)
    }

}