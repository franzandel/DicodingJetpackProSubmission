package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragmentVM
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentTvShowsBinding
import com.franzandel.dicodingjetpackprosubmission.databinding.LayoutErrorBinding
import com.franzandel.dicodingjetpackprosubmission.external.extension.hide
import com.franzandel.dicodingjetpackprosubmission.external.extension.show
import com.franzandel.dicodingjetpackprosubmission.external.extension.showShareMessage
import com.franzandel.dicodingjetpackprosubmission.instrumentedtest.EspressoIdlingResource
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

    private lateinit var errorViewBinding: LayoutErrorBinding

    private val adapter by lazy {
        TvShowsAdapter(requireContext())
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTvShowsBinding = FragmentTvShowsBinding.inflate(inflater, container, false)

    override fun onFragmentCreated() {
        errorViewBinding = viewBinding.layoutError
        setupObservers()
        setupListeners()
        EspressoIdlingResource.increment()
        tvShowsViewModel.getTvShows()
    }

    private fun setupObservers() {
        tvShowsViewModel.result.observe(viewLifecycleOwner, Observer { tvShows ->
            viewBinding.layoutError.root.hide()
            viewBinding.ablTvShows.show()
            viewBinding.rvTvShows.show()
            setupRV(tvShows)
            EspressoIdlingResource.decrement()
        })

        tvShowsViewModel.errorResult.observe(viewLifecycleOwner, Observer {
            viewBinding.layoutError.root.show()
            viewBinding.ablTvShows.hide()
            viewBinding.rvTvShows.hide()
            EspressoIdlingResource.decrement()
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

        errorViewBinding.btnTryAgain.setOnClickListener {
            tvShowsViewModel.getTvShows()
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