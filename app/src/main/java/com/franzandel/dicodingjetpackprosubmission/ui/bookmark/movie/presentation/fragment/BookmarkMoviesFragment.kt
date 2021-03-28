package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragmentVM
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentBookmarkMoviesBinding
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.adapter.BookmarkMoviesAdapter
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.viewmodel.BookmarkMoviesVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookmarkMoviesFragment : BaseFragmentVM<BookmarkMoviesVM, FragmentBookmarkMoviesBinding>() {

    @Inject
    lateinit var bookmarkMoviesVM: BookmarkMoviesVM

    override fun getVM(): BookmarkMoviesVM = bookmarkMoviesVM

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookmarkMoviesBinding =
        FragmentBookmarkMoviesBinding.inflate(inflater, container, false)

    override fun onFragmentCreated() {
        setupObservers()
        bookmarkMoviesVM.getBookmarkMovies()
    }

    private fun setupObservers() {
        bookmarkMoviesVM.bookmarkMovies.observe(
            viewLifecycleOwner,
            Observer { bookmarkMoviesResponse ->
                setupRV(bookmarkMoviesResponse)
            })
    }

    private fun setupRV(bookmarkMoviesResponse: List<BookmarkMovieResponse>) {
        val adapter =
            BookmarkMoviesAdapter(
                requireContext()
            )
        viewBinding.rvBookmarkMovies.adapter = adapter
        adapter.submitList(bookmarkMoviesResponse)
    }
}