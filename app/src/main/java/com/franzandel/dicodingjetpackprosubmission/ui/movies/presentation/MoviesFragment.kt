package com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragment
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentMoviesBinding
import com.franzandel.dicodingjetpackprosubmission.external.showShareMessage
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Result
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.remote.MoviesNetworkService
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository.MoviesRepositoryImpl
import com.google.gson.Gson

class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

    private val moviesViewModel by lazy {
//        ViewModelProvider(this).get(MoviesViewModel::class.java)
        MoviesViewModel(
            MoviesRepositoryImpl(
                MoviesNetworkService.getMoviesNetwork(),
                Gson()
            )
        )
    }

    private val adapter by lazy {
        MoviesAdapter(
            requireContext()
        )
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
        moviesViewModel.moviesResult.observe(viewLifecycleOwner, Observer { movies ->
            setupRV(movies.results)
        })
    }

    private fun setupRV(movies: List<Result>) {
        viewBinding.rvMovies.layoutManager = GridLayoutManager(
            requireContext(),
            GRID_SPAN_COUNT
        )
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