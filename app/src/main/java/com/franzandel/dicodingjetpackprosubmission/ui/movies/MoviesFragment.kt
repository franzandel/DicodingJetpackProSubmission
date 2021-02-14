package com.franzandel.dicodingjetpackprosubmission.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    private val adapter by lazy {
        MoviesAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupObservers()
//        moviesViewModel.getMovies()
        MoviesFragmentDirections.actionNavigationMoviesToDetailFragment()
    }

    private fun setupObservers() {
        moviesViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            fragmentMoviesBinding.rvMovies.adapter = adapter
            adapter.submitList(movies)
        })
    }
}