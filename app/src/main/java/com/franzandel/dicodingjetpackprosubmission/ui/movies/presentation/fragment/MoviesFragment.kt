package com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragmentVM
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentMoviesBinding
import com.franzandel.dicodingjetpackprosubmission.external.showShareMessage
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation.adapter.MoviesAdapter
import com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : BaseFragmentVM<MoviesViewModel, FragmentMoviesBinding>() {

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

    @Inject
    lateinit var moviesViewModel: MoviesViewModel

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
        moviesViewModel.result.observe(viewLifecycleOwner, Observer { movies ->
            setupRV(movies)
        })
    }

    private fun setupRV(movies: List<Movie>) {
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

    override fun getVM(): MoviesViewModel = moviesViewModel
}