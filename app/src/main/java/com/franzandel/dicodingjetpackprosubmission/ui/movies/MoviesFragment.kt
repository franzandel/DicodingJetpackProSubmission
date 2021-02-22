package com.franzandel.dicodingjetpackprosubmission.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragment
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentMoviesBinding
import com.franzandel.dicodingjetpackprosubmission.external.showShareMessage

class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

    private val moviesViewModel by lazy {
        ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    private val adapter by lazy {
        MoviesAdapter(requireContext())
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false)

    override fun onFragmentCreated() {
        setupObservers()
        setupListeners()
        moviesViewModel.getMovies()
    }

    private fun setupObservers() {
        moviesViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            setupRV(movies)
        })
    }

    private fun setupRV(movies: List<Movie>) {
        viewBinding.rvMovies.layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        viewBinding.rvMovies.adapter = adapter
        adapter.submitList(movies)
    }

    private fun setupListeners() {
        viewBinding.mtbMovies.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_share -> {
                    requireActivity().showShareMessage()
                    true
                }
                else -> false
            }
        }
    }
}