package com.franzandel.dicodingjetpackprosubmission.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragment
import com.franzandel.dicodingjetpackprosubmission.data.AppConsts
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentDetailBinding
import com.franzandel.dicodingjetpackprosubmission.ui.detail.adapter.DetailMovieAdapter
import com.franzandel.dicodingjetpackprosubmission.ui.detail.adapter.DetailTvShowAdapter
import com.google.android.material.snackbar.Snackbar

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private var isBookmarked = false
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

    private val detailMovieAdapter by lazy {
        DetailMovieAdapter(requireContext())
    }

    private val detailTvShowAdapter by lazy {
        DetailTvShowAdapter(requireContext())
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)

    override fun onFragmentCreated() {
        setupMoviesUI()
        setupTvShowsUI()
        setupListeners()
        setupRV()
    }

    private fun setupMoviesUI() {
        movies?.let {
            viewBinding.apply {
                toolbarDetail.title = it[currentPosition].title
                tvRelease.text = it[currentPosition].release_date
                tvGenre.text = it[currentPosition].popularity.toString()
                tvLength.text = it[currentPosition].vote_count.toString()
                tvRating.text = it[currentPosition].vote_average.toString()
                tvOverview.text = it[currentPosition].overview

                val imageUrl = AppConsts.baseUrlImage + it[currentPosition].poster_path
                Glide.with(requireContext())
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivDetail)
            }
        }
    }

    private fun setupTvShowsUI() {
        tvShows?.let {
            viewBinding.apply {
                toolbarDetail.title = it[currentPosition].title
                tvRelease.text = it[currentPosition].releaseYear
                tvGenre.text = it[currentPosition].genre
                tvLength.text = it[currentPosition].length
                tvRating.text = it[currentPosition].rating
                tvOverview.text = it[currentPosition].description
                ivDetail.setImageResource(it[currentPosition].image)
            }
        }
    }

    private fun setupListeners() {
        viewBinding.toolbarDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewBinding.fabBookmark.setOnClickListener {
            isBookmarked = !isBookmarked

            if (isBookmarked) {
                viewBinding.fabBookmark.setImageResource(R.drawable.ic_baseline_star_24)
                Snackbar.make(
                    it,
                    getString(R.string.detail_bookmark_added),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                viewBinding.fabBookmark.setImageResource(R.drawable.ic_baseline_star_border_24)
                Snackbar.make(
                    it,
                    getString(R.string.detail_bookmark_removed),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupRV() {
        viewBinding.rvDetail.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        movies?.let {
            it.removeAt(currentPosition)
            viewBinding.rvDetail.adapter = detailMovieAdapter
            detailMovieAdapter.submitList(movies)
        }

        tvShows?.let {
            it.removeAt(currentPosition)
            viewBinding.rvDetail.adapter = detailTvShowAdapter
            detailTvShowAdapter.submitList(tvShows)
        }
    }
}