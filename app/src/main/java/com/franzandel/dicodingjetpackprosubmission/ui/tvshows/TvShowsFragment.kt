package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragment
import com.franzandel.dicodingjetpackprosubmission.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentTvShowsBinding
import com.franzandel.dicodingjetpackprosubmission.external.showShareMessage

class TvShowsFragment : BaseFragment<FragmentTvShowsBinding>() {

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

    private val tvShowsViewModel by lazy {
        ViewModelProvider(this).get(TvShowsViewModel::class.java)
    }

    private val adapter by lazy {
        TvShowsAdapter(requireContext())
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTvShowsBinding = FragmentTvShowsBinding.inflate(inflater, container, false)

    override fun onFragmentCreated() {
        setupObservers()
        setupListeners()
        tvShowsViewModel.getTvShows()
    }

    private fun setupObservers() {
        tvShowsViewModel.tvShows.observe(viewLifecycleOwner, Observer { movies ->
            setupRV(movies)
        })
    }

    private fun setupListeners() {
        viewBinding.mtbTvShows.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_share -> {
                    requireActivity().showShareMessage()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRV(tvShows: List<TvShow>) {
        viewBinding.rvTvShows.layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        viewBinding.rvTvShows.adapter = adapter
        adapter.submitList(tvShows)
    }
}