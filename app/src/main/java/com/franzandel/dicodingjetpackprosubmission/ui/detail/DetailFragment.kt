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
        detailFragmentArgs.movies?.toMutableList() ?: mutableListOf()
    }

    private val currentPosition by lazy {
        detailFragmentArgs.currentPosition
    }

    private var isBookmarked = false

    private lateinit var fragmentDetailBinding: FragmentDetailBinding

    private val adapter by lazy {
        DetailAdapter(requireContext())
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
        fragmentDetailBinding.toolbar.title = movies[currentPosition].title
        fragmentDetailBinding.tvRelease.text = movies[currentPosition].releaseDate
        fragmentDetailBinding.tvGenre.text = movies[currentPosition].genre
        fragmentDetailBinding.tvLength.text = movies[currentPosition].length
        fragmentDetailBinding.tvRating.text = movies[currentPosition].rating
        fragmentDetailBinding.tvOverview.text = movies[currentPosition].desription
        fragmentDetailBinding.ivDetail.setImageResource(movies[currentPosition].image)

        fragmentDetailBinding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        fragmentDetailBinding.fabBookmark.setOnClickListener {
            isBookmarked = !isBookmarked

            if (isBookmarked) {
                fragmentDetailBinding.fabBookmark.setImageResource(R.drawable.ic_baseline_star_24)
                Snackbar.make(
                    view,
                    getString(R.string.detail_bookmark_added),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                fragmentDetailBinding.fabBookmark.setImageResource(R.drawable.ic_baseline_star_border_24)
                Snackbar.make(
                    view,
                    getString(R.string.detail_bookmark_removed),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        setupRV()
    }

    private fun setupRV() {
        fragmentDetailBinding.rvDetail.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        fragmentDetailBinding.rvDetail.adapter = adapter

        movies.removeAt(currentPosition)
        adapter.submitList(movies)
    }
}