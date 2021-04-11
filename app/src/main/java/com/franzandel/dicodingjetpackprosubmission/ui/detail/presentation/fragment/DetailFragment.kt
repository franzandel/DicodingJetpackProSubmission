package com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragmentVM
import com.franzandel.dicodingjetpackprosubmission.data.consts.ApiConsts
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentDetailBinding
import com.franzandel.dicodingjetpackprosubmission.external.extension.hide
import com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.adapter.DetailMovieAdapter
import com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.adapter.DetailTvShowAdapter
import com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.viewmodel.DetailViewModel
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        hideBottomNavigation()
        setupListeners()
        setupObservers()
        setupRV()
    }

    private fun hideBottomNavigation() {
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.navView)
        bottomNavigation.hide()
    }

    private fun setupObservers() {
        detailViewModel.isBookmarked.observe(
            viewLifecycleOwner,
            Observer { isBookmarked ->
                this.isBookmarked = isBookmarked
                viewBinding.fabBookmark.isSelected = isBookmarked
                val imageResource = if (isBookmarked)
                    R.drawable.ic_baseline_star_24
                else
                    R.drawable.ic_baseline_star_border_24

                viewBinding.fabBookmark.setImageResource(imageResource)
            })

        detailViewModel.bookmarkResult.observe(viewLifecycleOwner, Observer {
            isBookmarked = !isBookmarked
            setupBookmark()
        })

        detailViewModel.errorResult.observe(viewLifecycleOwner, Observer {
            setupBookmark()
        })
    }

    private fun setupBookmark() {
        viewBinding.fabBookmark.isSelected = isBookmarked

        if (isBookmarked) {
            viewBinding.fabBookmark.setImageResource(R.drawable.ic_baseline_star_24)
            val snackbar = Snackbar.make(
                requireView(),
                getString(R.string.detail_bookmark_added),
                Snackbar.LENGTH_SHORT
            )
            snackbar.setAction(getString(R.string.detail_bookmark_added_view)) {
                val navDirections =
                    DetailFragmentDirections.actionDetailFragmentToBookmarkActivity()
                Navigation.findNavController(it).navigate(navDirections)
            }
            snackbar.show()
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
            tvShows = it.map { tvShow -> tvShow.copy() }
            val tvShow = it[currentPosition]

            viewBinding.apply {
                toolbarDetail.title = tvShow.name
                tvRelease.text = tvShow.firstAirDate
                tvGenre.text = tvShow.popularity.toString()
                tvLength.text = tvShow.voteCount.toString()
                tvRating.text = tvShow.voteAverage.toString()
                tvOverview.text = tvShow.overview

                val imageUrl = ApiConsts.baseUrlImage + tvShow.posterPath
                Glide.with(requireContext())
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_not_found)
                    .into(ivDetail)
            }

            detailViewModel.getBookmarkTvShow(tvShow.id)
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
            val movie = movies[currentPosition]
            if (!isBookmarked)
                detailViewModel.addMovieToBookmark(movie)
            else
                detailViewModel.deleteMovieFromBookmark(movie.id)
        }

        tvShowsArgs?.let {
            val tvShow = tvShows[currentPosition]
            if (!isBookmarked)
                detailViewModel.addTvShowToBookmark(tvShow)
            else
                detailViewModel.deleteTvShowFromBookmark(tvShow.id)
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