package com.franzandel.dicodingjetpackprosubmission.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {

    private val detailFragmentArgs: DetailFragmentArgs by navArgs()
    private val movies by lazy {
        detailFragmentArgs.movies?.toMutableList()
    }

    private val tvShows by lazy {
        detailFragmentArgs.tvShows?.toMutableList()
    }

    private val currentPosition by lazy {
        detailFragmentArgs.currentPosition
    }

    private var isBookmarked = false

    private lateinit var fragmentDetailBinding: FragmentDetailBinding

    private val detailMovieAdapter by lazy {
        DetailMovieAdapter(requireContext())
    }

    private val detailTvShowAdapter by lazy {
        DetailTvShowAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentDetailBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return fragmentDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMoviesUI()
        setupTvShowsUI()
        setupListeners()
        setupRV()
    }

    private fun setupMoviesUI() {
        movies?.let {
            fragmentDetailBinding.toolbar.title = it[currentPosition].title
            fragmentDetailBinding.tvRelease.text = it[currentPosition].releaseDate
            fragmentDetailBinding.tvGenre.text = it[currentPosition].genre
            fragmentDetailBinding.tvLength.text = it[currentPosition].length
            fragmentDetailBinding.tvRating.text = it[currentPosition].rating
            fragmentDetailBinding.tvOverview.text = it[currentPosition].desription
            fragmentDetailBinding.ivDetail.setImageResource(it[currentPosition].image)
        }
    }

    private fun setupTvShowsUI() {
        tvShows?.let {
            fragmentDetailBinding.toolbar.title = it[currentPosition].title
            fragmentDetailBinding.tvRelease.text = it[currentPosition].releaseYear
            fragmentDetailBinding.tvGenre.text = it[currentPosition].genre
            fragmentDetailBinding.tvLength.text = it[currentPosition].length
            fragmentDetailBinding.tvRating.text = it[currentPosition].rating
            fragmentDetailBinding.tvOverview.text = it[currentPosition].desription
            fragmentDetailBinding.ivDetail.setImageResource(it[currentPosition].image)
        }
    }

    private fun setupListeners() {
        fragmentDetailBinding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        fragmentDetailBinding.fabBookmark.setOnClickListener {
            isBookmarked = !isBookmarked

            if (isBookmarked) {
                fragmentDetailBinding.fabBookmark.setImageResource(R.drawable.ic_baseline_star_24)
                Snackbar.make(
                    it,
                    getString(R.string.detail_bookmark_added),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                fragmentDetailBinding.fabBookmark.setImageResource(R.drawable.ic_baseline_star_border_24)
                Snackbar.make(
                    it,
                    getString(R.string.detail_bookmark_removed),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupRV() {
        fragmentDetailBinding.rvDetail.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        movies?.let {
            it.removeAt(currentPosition)
            fragmentDetailBinding.rvDetail.adapter = detailMovieAdapter
            detailMovieAdapter.submitList(movies)
        }

        tvShows?.let {
            it.removeAt(currentPosition)
            fragmentDetailBinding.rvDetail.adapter = detailTvShowAdapter
            detailTvShowAdapter.submitList(tvShows)
        }
    }
}