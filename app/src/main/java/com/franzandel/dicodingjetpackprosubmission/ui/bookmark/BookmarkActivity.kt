package com.franzandel.dicodingjetpackprosubmission.ui.bookmark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.databinding.ActivityBookmarkBinding
import com.franzandel.dicodingjetpackprosubmission.external.extension.showShareMessage
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.fragment.BookmarkMoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupTabAdapter()
    }

    private fun setupListeners() {
        binding.mtbBookmark.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_share -> {
                    showShareMessage()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupTabAdapter() {
        val fragments = listOf<Fragment>(BookmarkMoviesFragment())
        val tabAdapter = BookmarkTabAdapter(this, fragments, supportFragmentManager)
        binding.viewPager.adapter = tabAdapter
    }
}