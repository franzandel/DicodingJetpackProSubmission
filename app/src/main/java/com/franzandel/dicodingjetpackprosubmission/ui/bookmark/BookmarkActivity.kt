package com.franzandel.dicodingjetpackprosubmission.ui.bookmark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.databinding.ActivityBookmarkBinding
import com.franzandel.dicodingjetpackprosubmission.external.extension.showShareMessage
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.fragment.BookmarkMoviesFragment
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.fragment.BookmarkTvShowsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkActivity : AppCompatActivity() {

    lateinit var binding: ActivityBookmarkBinding

    val bookmarkMoviesFragment by lazy {
        BookmarkMoviesFragment()
    }

    val bookmarkTvShowsFragment by lazy {
        BookmarkTvShowsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupTabAdapter()
    }

    private fun setupListeners() {
        binding.mtbBookmark.setOnMenuItemClickListener { menuItem ->
            menuItem.isChecked = true

            when (menuItem.itemId) {
                R.id.menu_share -> {
                    showShareMessage()
                    true
                }
                R.id.menu_sort_title -> {
                    bookmarkMoviesFragment.bookmarkMoviesVM.getBookmarkMovies(SortChoice.TITLE)
                    bookmarkTvShowsFragment.bookmarkTvShowsVM.getBookmarkTvShows(SortChoice.TITLE)
                    true
                }
                R.id.menu_sort_rating -> {
                    bookmarkMoviesFragment.bookmarkMoviesVM.getBookmarkMovies(SortChoice.RATING)
                    bookmarkTvShowsFragment.bookmarkTvShowsVM.getBookmarkTvShows(SortChoice.RATING)
                    true
                }
                else -> false
            }
        }

        binding.mtbBookmark.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupTabAdapter() {
        val fragments = listOf<Fragment>(bookmarkMoviesFragment, bookmarkTvShowsFragment)
        val tabAdapter = BookmarkTabAdapter(this, fragments, supportFragmentManager)
        binding.viewPager.adapter = tabAdapter
    }
}