package com.franzandel.dicodingjetpackprosubmission.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentMoviesBinding
import com.franzandel.dicodingjetpackprosubmission.external.showShareMessage

class MoviesFragment : Fragment() {

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

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
        fragmentMoviesBinding.rvMovies.layoutManager =
            GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        fragmentMoviesBinding.rvMovies.adapter = adapter
        adapter.submitList(movies)
    }

    private fun setupListeners() {
        fragmentMoviesBinding.mtbMovies.setOnMenuItemClickListener { menuItem ->
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