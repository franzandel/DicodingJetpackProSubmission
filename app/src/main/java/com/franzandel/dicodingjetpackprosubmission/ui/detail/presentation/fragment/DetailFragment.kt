package com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragmentVM
import com.franzandel.dicodingjetpackprosubmission.data.consts.ApiConsts
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentDetailBinding
import com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.adapter.DetailMovieAdapter
import com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.adapter.DetailTvShowAdapter
import com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.viewmodel.DetailViewModel
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragmentVM<DetailViewModel, FragmentDetailBinding>() {

    @Inject
    lateinit var detailViewModel: DetailViewModel

    private var isBookmarked = false
    private val detailFragmentArgs: DetailFragmentArgs by navArgs()
    private lateinit var movies: List<Movie>
    private lateinit var tvShows: List<TvShow>

    private val moviesArgs by lazy {
        detailFragmentArgs.movies?.toMutableList()
    }

    private val tvShowsArgs by lazy {
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
        setupObservers()
        setupRV()
    }

    private fun setupObservers() {
        detailViewModel.isMovieBookmarked.observe(
            viewLifecycleOwner,
            Observer { isMovieBookmarked ->
                isBookmarked = isMovieBookmarked
                val imageResource = if (isMovieBookmarked)
                    R.drawable.ic_baseline_star_24
                else
                    R.drawable.ic_baseline_star_border_24

                viewBinding.fabBookmark.setImageResource(imageResource)
            })

        detailViewModel.bookmarkMovieResult.observe(viewLifecycleOwner, Observer {
            isBookmarked = !isBookmarked
            setupBookmark()
        })

        detailViewModel.errorResult.observe(viewLifecycleOwner, Observer {
            setupBookmark()
        })
    }

    private fun setupBookmark() {
        if (isBookmarked) {
            viewBinding.fabBookmark.setImageResource(R.drawable.ic_baseline_star_24)
            Snackbar.make(
                requireView(),
                getString(R.string.detail_bookmark_added),
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            viewBinding.fabBookmark.setImageResource(R.drawable.ic_baseline_star_border_24)
            Snackbar.make(
                requireView(),
                getString(R.string.detail_bookmark_removed),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupMoviesUI() {
        moviesArgs?.let {
            movies = it.map { movie -> movie.copy() }
            val movie = it[currentPosition]

            viewBinding.apply {
                toolbarDetail.title = movie.title
                tvRelease.text = movie.releaseDate
                tvGenre.text = movie.popularity.toString()
                tvLength.text = movie.voteCount.toString()
                tvRating.text = movie.voteAverage.toString()
                tvOverview.text = movie.overview

                val imageUrl = ApiConsts.baseUrlImage + movie.posterPath
                Glide.with(requireContext())
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivDetail)
            }

            detailViewModel.getBookmarkMovie(movie.id)
        }
    }

    private fun setupTvShowsUI() {
        tvShowsArgs?.let {
            tvShows = it
            viewBinding.apply {
                toolbarDetail.title = it[currentPosition].name
                tvRelease.text = it[currentPosition].firstAirDate
                tvGenre.text = it[currentPosition].popularity.toString()
                tvLength.text = it[currentPosition].voteCount.toString()
                tvRating.text = it[currentPosition].voteAverage.toString()
                tvOverview.text = it[currentPosition].overview

                val imageUrl = ApiConsts.baseUrlImage + it[currentPosition].posterPath
                Glide.with(requireContext())
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivDetail)
            }
        }
    }

    private fun setupListeners() {
        viewBinding.toolbarDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewBinding.fabBookmark.setOnClickListener {
            handleBookmarkClick()
        }
    }

    private fun handleBookmarkClick() {
        moviesArgs?.let {
            if (!isBookmarked)
                detailViewModel.addMovieToBookmark(movies[currentPosition])
            else
                detailViewModel.deleteMovieFromBookmark(movies[currentPosition].id)
        }
    }

    private fun setupRV() {
        viewBinding.rvDetail.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        moviesArgs?.let {
            it.removeAt(currentPosition)
            viewBinding.rvDetail.adapter = detailMovieAdapter
            detailMovieAdapter.submitList(moviesArgs)
        }

        tvShowsArgs?.let {
            it.removeAt(currentPosition)
            viewBinding.rvDetail.adapter = detailTvShowAdapter
            detailTvShowAdapter.submitList(tvShowsArgs)
        }
    }

    override fun getVM(): DetailViewModel = detailViewModel
}