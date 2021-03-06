package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragment
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentTvShowsBinding
import com.franzandel.dicodingjetpackprosubmission.external.showShareMessage
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.Result
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.remote.TvShowsNetworkService
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository.TvShowsRepositoryImpl
import com.google.gson.Gson

class TvShowsFragment : BaseFragment<FragmentTvShowsBinding>() {

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

    private val tvShowsViewModel by lazy {
//        ViewModelProvider(this).get(TvShowsViewModel::class.java)
        TvShowsViewModel(
            TvShowsRepositoryImpl(
                TvShowsNetworkService.getTvShowsNetwork(),
                Gson()
            )
        )
    }

    private val adapter by lazy {
        TvShowsAdapter(
            requireContext()
        )
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
        tvShowsViewModel.tvShowsResult.observe(viewLifecycleOwner, Observer { tvShows ->
            setupRV(tvShows.results)
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

    private fun setupRV(tvShows: List<Result>) {
        viewBinding.rvTvShows.layoutManager = GridLayoutManager(
            requireContext(),
            GRID_SPAN_COUNT
        )
        viewBinding.rvTvShows.adapter = adapter
        adapter.submitList(tvShows)
    }
}