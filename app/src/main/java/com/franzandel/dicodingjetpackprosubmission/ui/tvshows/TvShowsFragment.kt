package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentTvShowsBinding
import com.franzandel.dicodingjetpackprosubmission.external.showShareMessage

class TvShowsFragment : Fragment() {

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

    private lateinit var tvShowsViewModel: TvShowsViewModel
    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding

    private val adapter by lazy {
        TvShowsAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvShowsViewModel =
            ViewModelProvider(this).get(TvShowsViewModel::class.java)
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        fragmentTvShowsBinding.mtbTvShows.setOnMenuItemClickListener { menuItem ->
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
        fragmentTvShowsBinding.rvTvShows.layoutManager =
            GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        fragmentTvShowsBinding.rvTvShows.adapter = adapter
        adapter.submitList(tvShows)
    }
}