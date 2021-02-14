package com.franzandel.dicodingjetpackprosubmission.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {

    private val detailFragmentArgs: DetailFragmentArgs by navArgs()
    private val movie by lazy {
        detailFragmentArgs.movie
    }

    private var isBookmarked = false

    private lateinit var fragmentDetailBinding: FragmentDetailBinding

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
        fragmentDetailBinding.toolbar.title = movie?.title
        fragmentDetailBinding.tvRelease.text = movie?.releaseDate
        fragmentDetailBinding.tvGenre.text = movie?.genre
        fragmentDetailBinding.tvLength.text = movie?.length
        fragmentDetailBinding.tvRating.text = movie?.rating
        fragmentDetailBinding.tvOverview.text = movie?.desription
        fragmentDetailBinding.ivDetail.setImageResource(movie!!.image)

        fragmentDetailBinding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        fragmentDetailBinding.fab.setOnClickListener {
            isBookmarked = !isBookmarked

            if (isBookmarked) {
                fragmentDetailBinding.fab.setImageResource(R.drawable.ic_baseline_star_24)
                Snackbar.make(
                    view,
                    getString(R.string.detail_bookmark_added),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                fragmentDetailBinding.fab.setImageResource(R.drawable.ic_baseline_star_border_24)
                Snackbar.make(
                    view,
                    getString(R.string.detail_bookmark_removed),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}