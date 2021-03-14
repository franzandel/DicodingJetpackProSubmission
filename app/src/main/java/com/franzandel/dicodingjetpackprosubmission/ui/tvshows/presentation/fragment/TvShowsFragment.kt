package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragmentVM
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentTvShowsBinding
import com.franzandel.dicodingjetpackprosubmission.external.showShareMessage
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation.adapter.TvShowsAdapter
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation.viewmodel.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvShowsFragment : BaseFragmentVM<TvShowsViewModel, FragmentTvShowsBinding>() {

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

    @Inject
    lateinit var tvShowsViewModel: TvShowsViewModel

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
        tvShowsViewModel.result.observe(viewLifecycleOwner, Observer { tvShows ->
            setupRV(tvShows)
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
        viewBinding.rvTvShows.layoutManager = GridLayoutManager(
            requireContext(),
            GRID_SPAN_COUNT
        )
        viewBinding.rvTvShows.adapter = adapter
        adapter.submitList(tvShows)
    }

    override fun getVM(): TvShowsViewModel = tvShowsViewModel
}